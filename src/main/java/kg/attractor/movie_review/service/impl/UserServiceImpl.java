package kg.attractor.movie_review.service.impl;

import kg.attractor.movie_review.dao.UserDao;
import kg.attractor.movie_review.dto.UserDto;
import kg.attractor.movie_review.exception.UserNotFoundException;
import kg.attractor.movie_review.model.User;
import kg.attractor.movie_review.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    private final PasswordEncoder encoder;

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userDao.getUsers();
        List<UserDto> dtos = new ArrayList<>();
        users.forEach(e -> dtos.add(UserDto.builder()
                .email(e.getEmail())
                .password(e.getPassword())
                .build()));
        return dtos;
    }

    @Override
    public UserDto getUserById(String email) throws UserNotFoundException {
        User user = userDao.getUserById(email).orElseThrow(() -> new UserNotFoundException("Can not find user with ID: " + email));
        return UserDto.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    @Override
    public void createUser(UserDto user) {
        User u = new User();
        u.setEmail(user.getEmail());
        u.setUsername(user.getUsername());
        u.setPassword(encoder.encode(user.getPassword()));
        u.setEnabled(true);
        userDao.createUser(u);
    }

    @Override
    public void login(Authentication auth) {
        log.info(auth.getName());
    }
}
