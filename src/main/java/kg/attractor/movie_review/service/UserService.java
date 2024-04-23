package kg.attractor.movie_review.service;

import kg.attractor.movie_review.dto.UserDto;
import kg.attractor.movie_review.exception.UserNotFoundException;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();

    UserDto getUserById(String email) throws UserNotFoundException;

    void createUser(UserDto user);

    void login(Authentication auth);
}
