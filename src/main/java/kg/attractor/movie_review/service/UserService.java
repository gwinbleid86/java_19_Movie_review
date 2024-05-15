package kg.attractor.movie_review.service;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.movie_review.dto.UserDto;
import kg.attractor.movie_review.exception.UserNotFoundException;
import kg.attractor.movie_review.model.User;
import org.springframework.security.core.Authentication;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UserService {
    List<UserDto> getUsers();

    UserDto getUserById(String email) throws UserNotFoundException;

    void createUser(UserDto user);

    void login(Authentication auth);

    void makeResetPasswordLnk(HttpServletRequest request) throws MessagingException, UnsupportedEncodingException;

    User getByResetPasswordToken(String token);

    void updatePassword(User user, String password);
}
