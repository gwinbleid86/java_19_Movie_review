package kg.attractor.movie_review.service;

import kg.attractor.movie_review.dto.DirectorDto;
import kg.attractor.movie_review.model.Director;

public interface DirectorService {
    DirectorDto getDirectorDtoById(long id);

    Director getDirectorById(long id);
}
