package kg.attractor.movie_review.service;

import kg.attractor.movie_review.dto.MovieDto;
import kg.attractor.movie_review.dto.MovieDtoPaging;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface MovieService {
    List<MovieDto> getMovies();

    List<MovieDto> getMoviesSorted(String sortedBy, String sortValue);

    MovieDtoPaging getMoviesWithPaging(Integer page, Integer perPage);

    Page<MovieDto> getPageableMovies(Pageable pageable);

    void createMovie(MovieDto movieDto);

    Long createMovieAndReturnId(MovieDto movieDto);

    Map<String, Object> getMovieById(Long id);

    boolean deleteMovie(long id);

    void createMovie(MovieDto movieDto, String director, String castMemberName, String castMemberRole);
}
