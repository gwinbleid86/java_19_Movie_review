package kg.attractor.movie_review.service;

import kg.attractor.movie_review.dto.DirectorDto;

public interface DirectorService {
    DirectorDto getDirectorById(long id);
}
