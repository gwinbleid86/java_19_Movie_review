package kg.attractor.movie_review.dao;

import kg.attractor.movie_review.model.MovieImage;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MovieImageDao {

    private final JdbcTemplate jdbcTemplate;

    public void save(MovieImage movieImage) {
        String sql = """
                insert into movie_images(movie_id, file_name)
                values ( ?, ? );               
                """;
        jdbcTemplate.update(sql, movieImage.getMovieId(), movieImage.getFileName());
    }

    public Optional<MovieImage> getImageNameById(int imageId) {
        String sql = """
                select * from movie_images
                where id = ?;
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MovieImage.class), imageId)
                )
        );
    }
}
