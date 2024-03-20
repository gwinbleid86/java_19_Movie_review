package kg.attractor.movie_review.service.impl;

import kg.attractor.movie_review.dao.UserDao;
import kg.attractor.movie_review.dto.UserDto;
import kg.attractor.movie_review.exception.UserNotFoundException;
import kg.attractor.movie_review.model.User;
import kg.attractor.movie_review.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userDao.getUsers();
        List<UserDto> dtos = new ArrayList<>();
        users.forEach(e -> dtos.add(UserDto.builder()
                .id(e.getId())
                .email(e.getEmail())
                .password(e.getPassword())
                .build()));
        return dtos;
    }

    @Override
    public UserDto getUserById(String email) throws UserNotFoundException {
        User user = userDao.getUserById(email).orElseThrow(() -> new UserNotFoundException("Can not find user with ID: " + email));
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    @Override
    public void createUser(UserDto user) {
        userDao.createUser(user);
    }
}
