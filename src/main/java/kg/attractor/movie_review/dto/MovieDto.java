package kg.attractor.movie_review.dto;

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
    private Integer releaseYear;
    private String description;
    private DirectorDto director;
}
