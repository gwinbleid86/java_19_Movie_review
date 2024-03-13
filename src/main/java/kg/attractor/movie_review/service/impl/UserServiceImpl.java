package kg.attractor.movie_review.service;

import kg.attractor.movie_review.dao.UserDao;
import kg.attractor.movie_review.dto.UserDto;
import kg.attractor.movie_review.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    private final UserDao userDao;

    public List<UserDto> getUsers(){
        List<User> users = userDao.getUsers();
        List<UserDto> dtos = new ArrayList<>();
        users.forEach(e -> UserDto.builder()
                    .id(e.getId())
                    .name(e.getName())
                    .password(e.getPassword())
                    .build());
        return dtos;
    }
}
