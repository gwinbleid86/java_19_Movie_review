package kg.attractor.movie_review.service;

import kg.attractor.movie_review.dto.UserDto;
import kg.attractor.movie_review.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();

    UserDto getUserById(int id) throws UserNotFoundException;

    void createUser(UserDto user);
}
