package kg.attractor.movie_review.exception;

public class MovieNotFound extends RuntimeException {
    public MovieNotFound() {
    }

    public MovieNotFound(String message) {
        super(message);
    }
}
