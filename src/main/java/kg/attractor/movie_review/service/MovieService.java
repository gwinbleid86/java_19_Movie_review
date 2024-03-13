package kg.attractor.movie_review.service;

import kg.attractor.movie_review.dto.MovieDto;
import kg.attractor.movie_review.exception.MovieNotFoundException;

import java.util.List;

public interface MovieService {
    List<MovieDto> getMovies();

    void createMovie(MovieDto movieDto);

    Long createMovieAndReturnId(MovieDto movieDto);

    MovieDto getMovieById(Long id) throws MovieNotFoundException;
}
