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
}
