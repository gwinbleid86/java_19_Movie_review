package kg.attractor.movie_review.controller;

import kg.attractor.movie_review.dto.DirectorDto;
import kg.attractor.movie_review.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("directors")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService directorService;

    @GetMapping("{id}")
    public ResponseEntity<?> getDirectorById(@PathVariable long id) {
        DirectorDto director = directorService.getDirectorById(id);
        if (director == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Director not found");
        }
        return ResponseEntity.ok(director);
    }
}
