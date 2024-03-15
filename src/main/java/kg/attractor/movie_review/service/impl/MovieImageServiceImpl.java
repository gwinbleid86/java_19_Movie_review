package kg.attractor.movie_review.service.impl;

import kg.attractor.movie_review.dao.MovieImageDao;
import kg.attractor.movie_review.dto.MovieImageDto;
import kg.attractor.movie_review.model.MovieImage;
import kg.attractor.movie_review.service.MovieImageService;
import kg.attractor.movie_review.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieImageServiceImpl implements MovieImageService {

    private final MovieImageDao movieImageDao;

    private final FileUtil fileUtil;

    @Override
    public void upload(MovieImageDto movieImageDto) {
        String filename = fileUtil.saveUploadedFile(movieImageDto.getFile(), "images");
        MovieImage movieImage = new MovieImage();
        movieImage.setMovieId(movieImageDto.getMovieId());
        movieImage.setFileName(filename);

        movieImageDao.save(movieImage);
    }
}
