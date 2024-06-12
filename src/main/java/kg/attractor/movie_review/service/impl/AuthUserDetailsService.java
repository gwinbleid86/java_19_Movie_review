package kg.attractor.movie_review.service.impl;

import kg.attractor.movie_review.model.Authority;
import kg.attractor.movie_review.repository.AuthorityRepository;
import kg.attractor.movie_review.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private final AuthorityRepository authorityRepository;

    private final PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.getByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new User(user.getEmail(), user.getPassword(), getAuthorities(user.getAuthorities()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(List<Authority> authorities) {
        return authorities.stream()
                .map(e -> new SimpleGrantedAuthority(e.getRole()))
                .toList();
    }

    public void processOAuthPostLogin(String username) {
        var existUser = userRepository.getByEmail(username);

        if (existUser.isEmpty()) {
            var user = new kg.attractor.movie_review.model.User();
            user.setEmail(username);
            user.setUsername(username);
            user.setPassword(encoder.encode("qwerty"));
            user.setEnabled(Boolean.TRUE);
            user.setAuthorities(new ArrayList<>());
            user.addAuthority(authorityRepository.findByRole("USER"));

            userRepository.saveAndFlush(user);
        }

        UserDetails userDetails = loadUserByUsername(username);
        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
