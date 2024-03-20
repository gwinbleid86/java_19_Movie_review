package kg.attractor.movie_review.exception;

import java.util.NoSuchElementException;

public class MovieNotFoundException extends NoSuchElementException {
    public MovieNotFoundException(String s) {
        super(s);
    }
}
