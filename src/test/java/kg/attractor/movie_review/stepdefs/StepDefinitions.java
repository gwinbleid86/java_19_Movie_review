package kg.attractor.movie_review.stepdefs;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import kg.attractor.movie_review.dto.MovieDto;
import kg.attractor.movie_review.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StepDefinitions {

    private final MovieService movieService;

    private MovieDto movieDto;

    @Autowired
    public StepDefinitions(MovieService movieService) {
        this.movieService = movieService;
    }

    @Когда("я запрашиваю фильм с id {long}")
    public void яЗапрашиваюФильмСId(long movieId) {
        Map<String, Object> model = movieService.getMovieById(movieId);
        movieDto = (MovieDto) model.get("movie");
        assertEquals(movieId, movieDto.getId());

    }

    @Тогда("я получаю информацию по фильму")
    public void яПолучаюИнформациюПоФильму() {
        assertNotNull(movieDto);
    }
}
