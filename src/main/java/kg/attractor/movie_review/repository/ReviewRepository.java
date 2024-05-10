package kg.attractor.movie_review.repository;

import kg.attractor.movie_review.model.Review;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByMovie_IdOrderByRatingDesc(Long movieId);

    //    @Query("select r from Review r where r.movie.id = :movieId")
    List<Review> findByMovie_Id(Long movieId, Sort sort);
}
