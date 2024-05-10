package kg.attractor.movie_review.service.impl;

import kg.attractor.movie_review.dao.DirectorDao;
import kg.attractor.movie_review.dto.DirectorDto;
import kg.attractor.movie_review.model.Director;
import kg.attractor.movie_review.repository.DirectorRepository;
import kg.attractor.movie_review.service.DirectorService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {

    private final DirectorDao directorDao;

    private final DirectorRepository directorRepository;

    @Override
    public DirectorDto getDirectorDtoById(long id) {
        try {
//            Director director = directorDao.getDirectorById(id)
            Director director = directorRepository.findById(id)
                    .orElseThrow(Exception::new);
            return DirectorDto.builder()
                    .id(director.getId())
                    .fullName(director.getFullName())
                    .build();
        } catch (Exception e) {
            log.error("Can not find Director by ID: {}", id);
        }
        return null;
    }

    @SneakyThrows
    @Override
    public Director getDirectorById(long id) {
        return directorRepository.findById(id).orElseThrow(Exception::new);
    }
}
