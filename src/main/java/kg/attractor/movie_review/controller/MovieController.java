package kg.attractor.movie_review.controller;

import kg.attractor.movie_review.dto.MovieDto;
import kg.attractor.movie_review.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public String getMovies(
            Model model,
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        model.addAttribute("movies", movieService.getPageableMovies(pageable));

        return "movies/index";
    }

    @GetMapping("info/{movieId}")
    public String getInfo(@PathVariable Long movieId, Model model) {
        model.addAllAttributes(movieService.getMovieById(movieId));
        return "movies/movie_info";
    }

    @GetMapping("add")
    public String addMovie() {
        return "movies/create_movie";
    }

    @PostMapping("add")
    public String addMovie(
            MovieDto movieDto,
            @RequestParam(name = "directorName") String director,
            @RequestParam(name = "castMemberName") String castMemberName,
            @RequestParam(name = "castMemberRole") String castMemberRole
    ) {
        movieService.createMovie(movieDto, director, castMemberName, castMemberRole);
        return "redirect:/";
    }

    @GetMapping("api/movies")
    public String apiMoviePage() {
        return "api/movies";
    }
}
