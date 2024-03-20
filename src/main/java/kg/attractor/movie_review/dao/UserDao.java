package kg.attractor.movie_review.dao;

import kg.attractor.movie_review.dto.UserDto;
import kg.attractor.movie_review.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<User> getUsers() {
        String sql = """
                select * from user_table;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public Optional<User> getUserById(String email) {
        String sql = """
                select * from user_table
                where email = ?;
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, new BeanPropertyRowMapper<>(User.class), email)
                )
        );
    }

    public void createUser(UserDto user) {
        String sql = "insert into user_table(email, password) " +
                "values(:email, :password);";
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword()));
    }
}
