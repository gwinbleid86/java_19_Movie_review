package kg.attractor.movie_review.controller;

import kg.attractor.movie_review.dto.UserDto;
import kg.attractor.movie_review.exception.UserNotFoundException;
import kg.attractor.movie_review.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("users")
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        try {
            UserDto user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("users")
    public HttpStatus createUser(UserDto user) {
        userService.createUser(user);
        return HttpStatus.OK;
    }

//    @GetMapping("users/{id}/profile") http://localhost:8089/users/12/profile

}
