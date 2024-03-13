package kg.attractor.movie_review.dao;

import kg.attractor.movie_review.model.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MovieDao {
    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Movie> getMovies() {
        String sql = """
                select * from movie;
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Movie.class));
    }

    public Long createAndReturnId(Movie movie) {
        String sql = """
                insert into movie (name, release_year, description, director_id)
                values (?,?,?,?);
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, movie.getName());
            ps.setInt(2, movie.getReleaseYear());
            ps.setString(3, movie.getDescription());
            ps.setLong(4, movie.getDirectorId());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public void crate(Movie movie) {
        String sql = """
                insert into movie (name, release_year, description, director_id)
                values (:name, :release_year, :description, :director_id);
                """;
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("name", movie.getName())
                .addValue("release_year", movie.getReleaseYear())
                .addValue("description", movie.getDescription())
                .addValue("director_id", movie.getDirectorId()));
    }

    public Optional<Movie> getById(Long id) {
        String sql = """
                      select * from movie
                      where id = ?;          
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Movie.class), id)
                )
        );
    }
}
