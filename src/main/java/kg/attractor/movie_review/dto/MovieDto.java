package kg.attractor.movie_review.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private Long id;

    private String name;

    @JsonProperty("release_year")
    private Integer releaseYear;

    private String description;

    private DirectorDto director;
}
