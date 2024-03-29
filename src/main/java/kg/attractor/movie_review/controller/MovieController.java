package kg.attractor.movie_review.controller;

import kg.attractor.movie_review.dto.MovieDto;
import kg.attractor.movie_review.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieDto>> getMovies() {
        return ResponseEntity.ok(movieService.getMovies());
    }

    @GetMapping("sorting")
    public ResponseEntity<List<MovieDto>> getMoviesSorted(
            @RequestParam(name = "sortedBy", defaultValue = "name") String sortedBy,
            @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection
    ) {
        return ResponseEntity.ok(movieService.getMoviesSorted(sortedBy, sortDirection));
    }

    @GetMapping("paging")
    public ResponseEntity<List<MovieDto>> getMoviesWithPaging(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "2") Integer pageSize
    ) {
        return ResponseEntity.ok(movieService.getMoviesWithPaging(page, pageSize));
    }

    //@RequestMapping(name = "{id}", method = RequestMethod.GET)
    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    //    @PostMapping("add-and-return-id")
//    public ResponseEntity<Long> createAndReturnId(@RequestBody MovieDto movieDto) {
//        return ResponseEntity.ok(movieService.createMovieAndReturnId(movieDto));
//    }
//
    @PostMapping
    public HttpStatus create(@RequestBody MovieDto movieDto) {
        movieService.createMovie(movieDto);
        return HttpStatus.OK;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable long id) {
        if (movieService.deleteMovie(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
