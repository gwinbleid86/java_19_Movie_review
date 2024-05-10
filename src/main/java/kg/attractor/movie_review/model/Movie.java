package kg.attractor.movie_review.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "release_year")
    private Integer releaseYear;

    private String description;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "movie")
    private Review review;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    private List<MovieCastMember> movieCastMembers;
}
