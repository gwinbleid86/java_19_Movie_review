package kg.attractor.movie_review.repository;

import kg.attractor.movie_review.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = "select * from movie m " +
            "where m.DIRECTOR_ID = (" +
            "select id from DIRECTOR where FULLNAME = :name" +
            ")", nativeQuery = true)
//    @Query("select m from Movie m where Director.fullName = :name")
    List<Movie> findByDirectorName(String name);

//    List<Movie> findByDirector_FullName(String name);

    @Query(
            """
                    select m.id,
                        m.releaseYear,
                        m.description,
                        m.director
                    from Movie m
                    inner join MovieCastMember mcm on m.id = mcm.movie.id
                    inner join CastMember cm on mcm.castMember.id = cm.id
                    where m.releaseYear > :releaseYear
                    and cm.fullName like '%:actorName%'
                    """
    )
    List<Movie> selectMoviesByActorAndReleaseYear(String actorName, int releaseYear);


}
