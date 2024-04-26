package kg.attractor.movie_review.service.impl;

import kg.attractor.movie_review.common.SortStrategy;
import kg.attractor.movie_review.dao.MovieDao;
import kg.attractor.movie_review.dto.MovieDto;
import kg.attractor.movie_review.dto.MovieDtoPaging;
import kg.attractor.movie_review.exception.MovieNotFound;
import kg.attractor.movie_review.model.Movie;
import kg.attractor.movie_review.service.DirectorService;
import kg.attractor.movie_review.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;

    private final DirectorService directorService;


    private List<MovieDto> getListMovie(List<Movie> list) {
        List<MovieDto> movies = new ArrayList<>();

        list.forEach(e -> movies.add(MovieDto.builder()
                .id(e.getId())
                .name(e.getName())
                .description(e.getDescription())
                .releaseYear(e.getReleaseYear())
                .director(directorService.getDirectorById(e.getDirectorId()))
                .build()));
        return movies;
    }

    @Override
    public List<MovieDto> getMovies() {
        var movies = movieDao.getMovies();
        return getListMovie(movies);
    }

    @Override
    public List<MovieDto> getMoviesSorted(String sortedBy, String sortDirection) {
        var movies = movieDao.getMovies();

        var sortedMovies = SortStrategy.fromString(sortedBy).sortingMovies(movies);

        if (sortDirection.equalsIgnoreCase("desc")) {
            Collections.reverse(sortedMovies);
        }
        return getListMovie(sortedMovies);
    }

    @Override
    public MovieDtoPaging getMoviesWithPaging(Integer page, Integer pageSize) {
        int count = movieDao.getCount();
        int totalPages = count / pageSize;
        if (count % pageSize != 0) {
            totalPages++;
        }

        if (totalPages <= page) {
            page = totalPages;
        } else if (page < 0) {
            page = 0;
        }

        int offset = page * pageSize;

        List<MovieDto> movies = new ArrayList<>();
        List<Movie> list = movieDao.getMovies(pageSize, offset);

        list.forEach(e -> movies.add(MovieDto.builder()
                .id(e.getId())
                .name(e.getName())
                .description(e.getDescription())
                .releaseYear(e.getReleaseYear())
                .director(directorService.getDirectorById(e.getDirectorId()))
                .build()));

        var moviePaging = MovieDtoPaging.builder()
                .movies(movies)
                .totalPage(totalPages)
                .pageSize(pageSize)
                .page(page)
                .build();
        return moviePaging;
    }

    @Override
    public void createMovie(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setId(movieDto.getId());
        movie.setName(movieDto.getName());
        movie.setReleaseYear(movieDto.getReleaseYear());
        movie.setDescription(movieDto.getDescription());
        movie.setDirectorId(movieDto.getDirector().getId());

        movieDao.crate(movie);
    }

    @Override
    public Long createMovieAndReturnId(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setId(movieDto.getId());
        movie.setName(movieDto.getName());
        movie.setReleaseYear(movieDto.getReleaseYear());
        movie.setDescription(movieDto.getDescription());
        movie.setDirectorId(movieDto.getDirector().getId());

        return movieDao.createAndReturnId(movie);
    }

    @Override
    public MovieDto getMovieById(Long id) {
        Optional<Movie> mayBeMovie = movieDao.getById(id);
//                .orElseThrow(() -> new MovieNotFoundException("Can not find Movie with ID: " + id));
        if (mayBeMovie.isEmpty()) {
            log.error("Can not find Movie with ID: {}", id);
            throw new MovieNotFound("Can not find Movie with ID: " + id);
        }
        Movie movie = mayBeMovie.get();

        return MovieDto.builder()
                .id(movie.getId())
                .name(movie.getName())
                .releaseYear(movie.getReleaseYear())
                .description(movie.getDescription())
                .director(directorService.getDirectorById(movie.getDirectorId()))
                .build();
    }

    @Override
    public boolean deleteMovie(long id) {
        if (movieDao.getById(id).isPresent()) {
            movieDao.delete(id);
            return true;
        }
        return false;
    }

    @Override
    public void createMovie(MovieDto movieDto, String director, String castMemberName, String castMemberRole) {

    }
}
