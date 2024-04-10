package kg.attractor.movie_review.service;

import kg.attractor.movie_review.dto.MovieImageDto;
import org.springframework.http.ResponseEntity;

public interface MovieImageService {
    void upload(MovieImageDto movieImageDto);

    ResponseEntity<?> download(int imageId);
}
