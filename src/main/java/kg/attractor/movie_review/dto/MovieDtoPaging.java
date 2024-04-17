package kg.attractor.movie_review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDtoPaging {
    private List<MovieDto> movies;

    /* PAGING */

    private int page;
    private int pageSize;
    private int totalPage;
}
