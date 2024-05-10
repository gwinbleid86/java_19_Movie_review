package kg.attractor.movie_review.service.impl;

import kg.attractor.movie_review.dao.MovieDao;
import kg.attractor.movie_review.dto.DirectorDto;
import kg.attractor.movie_review.dto.MovieDto;
import kg.attractor.movie_review.dto.MovieDtoPaging;
import kg.attractor.movie_review.exception.MovieNotFound;
import kg.attractor.movie_review.model.Movie;
import kg.attractor.movie_review.repository.MovieRepository;
import kg.attractor.movie_review.service.DirectorService;
import kg.attractor.movie_review.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;

    private final DirectorService directorService;

    private final MovieRepository movieRepository;


    private List<MovieDto> getListMovie(List<Movie> list) {
        List<MovieDto> movies = new ArrayList<>();

        list.forEach(e -> movies.add(MovieDto.builder()
                .id(e.getId())
                .name(e.getName())
                .description(e.getDescription())
                .releaseYear(e.getReleaseYear())
                .director(DirectorDto.builder()
                        .id(e.getDirector().getId())
                        .fullName(e.getDirector().getFullName())
                        .build())
                .build()));
        return movies;
    }

    @Override
    public List<MovieDto> getMovies() {
        var movies = movieRepository.findAll();
//        var movies = movieDao.getMovies();
        return getListMovie(movies);
    }

    @Override
    public List<MovieDto> getMoviesSorted(String sortedBy, String sortDirection) {
        Sort.Order order;
        if (sortDirection.equalsIgnoreCase("desc")) {
            order = Sort.Order.desc(sortedBy);
        } else {
            order = Sort.Order.asc(sortedBy);
        }
        Sort sort = Sort.by(order);

        List<Movie> movies = movieRepository.findAll(sort);
        return getListMovie(movies);
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
                .director(directorService.getDirectorDtoById(e.getDirector().getId()))
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
    public Page<MovieDto> getPageableMovies(Pageable pageable) {

//        Pageable pageable = PageRequest.of(Integer.parseInt(pageNumber), Integer.parseInt(pageSize));

        Page<Movie> list = movieRepository.findAll(pageable);
        var movies = getListMovie(list.getContent());
        return new PageImpl<>(movies, pageable, list.getTotalPages());
    }

    @Override
    public void createMovie(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setId(movieDto.getId());
        movie.setName(movieDto.getName());
        movie.setReleaseYear(movieDto.getReleaseYear());
        movie.setDescription(movieDto.getDescription());
        movie.setDirector(directorService.getDirectorById(movieDto.getDirector().getId()));

//        movieDao.crate(movie);
        movieRepository.save(movie);
    }

    @Override
    public Long createMovieAndReturnId(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setId(movieDto.getId());
        movie.setName(movieDto.getName());
        movie.setReleaseYear(movieDto.getReleaseYear());
        movie.setDescription(movieDto.getDescription());
        movie.setDirector(directorService.getDirectorById(movieDto.getDirector().getId()));

//        return movieDao.createAndReturnId(movie);
        return movieRepository.save(movie).getId();
    }

    @Override
    public Map<String, Object> getMovieById(Long id) {
        Optional<Movie> mayBeMovie = movieRepository.findById(id);
//        Optional<Movie> mayBeMovie = movieDao.getById(id);
//                .orElseThrow(() -> new MovieNotFoundException("Can not find Movie with ID: " + id));
        if (mayBeMovie.isEmpty()) {
            log.error("Can not find Movie with ID: {}", id);
            throw new MovieNotFound("Can not find Movie with ID: " + id);
        }
        Movie movie = mayBeMovie.get();

        var m = MovieDto.builder()
                .id(movie.getId())
                .name(movie.getName())
                .releaseYear(movie.getReleaseYear())
                .description(movie.getDescription())
                .director(DirectorDto.builder()
                        .id(movie.getDirector().getId())
                        .fullName(movie.getDirector().getFullName())
                        .build())
                .build();

        return Map.of("movie", m);
    }

    @Override
    public boolean deleteMovie(long id) {
//        if (movieDao.getById(id).isPresent()) {
//            movieDao.delete(id);
//            return true;
//        }
        movieRepository.deleteById(id);
        return !movieRepository.existsById(id);
    }

    @Override
    public void createMovie(MovieDto movieDto, String director, String castMemberName, String castMemberRole) {

    }
}
