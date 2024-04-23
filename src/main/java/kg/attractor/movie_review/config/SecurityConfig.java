package kg.attractor.movie_review.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final DataSource dataSource;

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(encoder.encode("admin"))
//                .roles("ADMIN")
//                .authorities("FULL")
//                .build();
//
//        UserDetails guest = User.builder()
//                .username("quest")
//                .password(encoder.encode("qwe"))
//                .roles("GUEST")
//                .authorities("READ_ONLY")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, guest);
//    }

    private static final String USER_QUERY = "select email, password, enabled from user_table where email = ?;";
    private static final String AUTHORITIES_QUERY = """
            select ua.user_email, a.role from user_authority ua, authorities a
            where ua.authority_id = a.id
            and ua.user_email = ?;
            """;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(USER_QUERY)
                .authoritiesByUsernameQuery(AUTHORITIES_QUERY)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .httpBasic(Customizer.withDefaults())
                .formLogin(form -> form
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/auth/login?error=true")
                        .permitAll())
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/users").hasAuthority("ADMIN")
                        .requestMatchers("/movies/**").hasAuthority("ADMIN")
                        .requestMatchers("/api/**").permitAll()
                        .anyRequest().permitAll()
                )
                .exceptionHandling(Customizer.withDefaults())
        ;
        return http.build();
    }
}
