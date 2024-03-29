package kg.attractor.movie_review.exception;

import java.util.NoSuchElementException;

public class SortedCriteriaException extends NoSuchElementException {
    public SortedCriteriaException(String m) {
        super(m);
    }
}
