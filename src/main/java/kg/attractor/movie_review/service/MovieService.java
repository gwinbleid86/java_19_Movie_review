package kg.attractor.movie_review.service;

import kg.attractor.movie_review.dto.MovieDto;

import java.util.List;

public interface MovieService {
    List<MovieDto> getMovies();

    List<MovieDto> getMoviesSorted(String sortedBy, String sortValue);

    List<MovieDto> getMoviesWithPaging(Integer page, Integer perPage);

    void createMovie(MovieDto movieDto);

    Long createMovieAndReturnId(MovieDto movieDto);

    MovieDto getMovieById(Long id);

    boolean deleteMovie(long id);

    void createMovie(MovieDto movieDto, String director, String castMemberName, String castMemberRole);
}
