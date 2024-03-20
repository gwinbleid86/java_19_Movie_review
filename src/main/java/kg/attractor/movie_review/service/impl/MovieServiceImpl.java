package kg.attractor.movie_review.service.impl;

import kg.attractor.movie_review.dao.MovieDao;
import kg.attractor.movie_review.dto.MovieDto;
import kg.attractor.movie_review.model.Movie;
import kg.attractor.movie_review.service.DirectorService;
import kg.attractor.movie_review.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;

    private final DirectorService directorService;

    @Override
    public List<MovieDto> getMovies() {
        List<Movie> list = movieDao.getMovies();
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
        Movie movie = movieDao.getById(id)
                .orElseThrow(() -> new NoSuchElementException("Can not find Movie with ID: " + id));

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
}
