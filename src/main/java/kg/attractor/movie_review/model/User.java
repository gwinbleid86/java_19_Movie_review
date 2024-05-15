package kg.attractor.movie_review.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user_table")
public class User {

    @Id
    private String email;
    private String username;
    private String password;
    private Boolean enabled;
    private String resetPasswordToken;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "reviewer")
    private Review review;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "users", cascade = CascadeType.ALL)
    private List<Authority> authorities;

}
