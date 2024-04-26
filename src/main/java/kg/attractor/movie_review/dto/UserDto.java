package kg.attractor.movie_review.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    //    @NotBlank
    private String name;

    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 4, max = 24, message = "Length must be >= 4 and <= 24")
    @Pattern(
            regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$",
            message = "Should contain at least one UPPER case letter, one number"
    )
    private String password;
}
