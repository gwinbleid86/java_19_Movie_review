package kg.attractor.movie_review.service.impl;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.movie_review.dao.UserDao;
import kg.attractor.movie_review.dto.UserDto;
import kg.attractor.movie_review.exception.UserNotFoundException;
import kg.attractor.movie_review.model.User;
import kg.attractor.movie_review.repository.UserRepository;
import kg.attractor.movie_review.service.EmailService;
import kg.attractor.movie_review.service.UserService;
import kg.attractor.movie_review.util.Utility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    private final EmailService emailService;

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

    @Override
    public void makeResetPasswordLnk(HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        String email = request.getParameter("email");
        String token = UUID.randomUUID().toString();
        updateResetPasswordToken(token, email);

        String resetPasswordLnk = Utility.getSiteUrl(request) + "/auth/reset_password?token=" + token;
        emailService.sendEmail(email, resetPasswordLnk);
    }

    private void updateResetPasswordToken(String token, String email) {
        User user = userRepository.getByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by email: " + email));
        user.setResetPasswordToken(token);
        userRepository.saveAndFlush(user);
    }

    @Override
    public User getByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by token: " + token));
    }

    @Override
    public void updatePassword(User user, String password) {
        String encodedPassword = encoder.encode(password);
        user.setPassword(encodedPassword);
        user.setResetPasswordToken(null);
        userRepository.saveAndFlush(user);
    }
}
