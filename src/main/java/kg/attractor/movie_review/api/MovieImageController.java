package kg.attractor.movie_review.api;

import kg.attractor.movie_review.dto.MovieImageDto;
import kg.attractor.movie_review.service.MovieImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("{imageId}")
    public ResponseEntity<?> download(@PathVariable int imageId) {
        return movieImageService.download(imageId);
    }
}
