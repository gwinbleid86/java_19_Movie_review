package kg.attractor.movie_review.common;

import kg.attractor.movie_review.exception.SortedCriteriaException;
import kg.attractor.movie_review.model.Movie;

import java.util.Comparator;
import java.util.List;

public enum SortStrategy {
    BY_ID("id") {
        @Override
        public List<Movie> sortingMovies(List<Movie> movies) {
            movies.sort(Comparator.comparing(Movie::getId));
            return movies;
        }
    },
    BY_NAME("name") {
        @Override
        public List<Movie> sortingMovies(List<Movie> movies) {
            movies.sort(Comparator.comparing(Movie::getName));
            return movies;
        }
    },
    BY_YEAR("year") {
        @Override
        public List<Movie> sortingMovies(List<Movie> movies) {
            movies.sort(Comparator.comparing(Movie::getReleaseYear));
            return movies;
        }
    };

    private final String value;

    SortStrategy(String sortedBy) {
        this.value = sortedBy;
    }

    public static SortStrategy fromString(String sortedBy) {
        for (var e : SortStrategy.values()) {
            if (e.value.equalsIgnoreCase(sortedBy)) {
                return e;
            }
        }
        throw new SortedCriteriaException("Sorted criteria not found");
    }

    public abstract List<Movie> sortingMovies(List<Movie> movies);

}
