package kg.attractor.movie_review.controller;

import kg.attractor.movie_review.dto.MovieImageDto;
import kg.attractor.movie_review.service.MovieImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("images")
@RequiredArgsConstructor
public class MovieImageController {

    private final MovieImageService movieImageService;

    @PostMapping
    public ResponseEntity<Void> upload(MovieImageDto movieImageDto) {
        movieImageService.upload(movieImageDto);
        return ResponseEntity.ok().build();
    }
}
