package kg.attractor.movie_review.service;

import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface EmailService {
    void sendEmail(String toEmail, String link) throws MessagingException, UnsupportedEncodingException;
}
