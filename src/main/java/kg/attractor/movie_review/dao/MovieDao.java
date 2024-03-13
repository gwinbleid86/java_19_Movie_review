package kg.attractor.movie_review.dao;

import kg.attractor.movie_review.model.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MovieDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Movie> getMovies() {
        String sql = """
                select * from movie;
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Movie.class));
    }
}
