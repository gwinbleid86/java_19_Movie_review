package kg.attractor.movie_review.service.impl;

import kg.attractor.movie_review.model.Review;
import kg.attractor.movie_review.repository.ReviewRepository;
import kg.attractor.movie_review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public List<Review> findSortedReviews(Long movieId, String sortedCriteria) {
        Sort s1 = Sort.by(Sort.Order.asc(sortedCriteria));
        var list = reviewRepository.findByMovie_Id(movieId, s1);

        return list;
    }
}
