package com.example.authentication.service;

import com.example.authentication.client.UserClient;
import com.example.authentication.entity.User;
import com.example.authentication.mapper.UserMapper;
import com.example.authentication.config.ApplicationProperties;
import com.example.authentication.exception.AppException;
import com.example.authentication.model.request.EmailBodyRequest;
import com.example.authentication.model.request.EmailRequest;
import com.example.authentication.model.request.SignInRequest;
import com.example.authentication.model.request.UserInfoRequest;
import com.example.authentication.model.response.TokenResponse;
import com.example.authentication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static com.example.authentication.mapper.UserMapper.USER_MAPPER;
import static com.example.authentication.model.constants.ExceptionConstants.DUPLICATE_EMAIL_EXCEPTION;
import static com.example.authentication.model.constants.ExceptionConstants.NOT_FOUND_EXCEPTION;


@Log4j2
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserClient userClient;
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ApplicationProperties applicationProperties;
    private final EmailSenderService emailSenderService;
    private final EmailService emailService;

    public void fillUserInfo(UserInfoRequest signupRequest) {

        var emailRequest = EmailRequest.builder()
                .email(signupRequest.username())
                .build();

        emailService.sendEmail(emailRequest);
        var user = User.builder().password(bCryptPasswordEncoder.encode(signupRequest.password())).build();
        userRepository.save(user);

        CompletableFuture.runAsync(() ->
                userClient.createUser(USER_MAPPER.mapToClient(signupRequest)));
    }

    public TokenResponse signIn(SignInRequest signInRequest) {
        var user = userRepository.findByEmail(signInRequest.email())
                .orElseThrow(() -> new AppException(String.format("user not found by email: %s",
                        signInRequest.email()), NOT_FOUND_EXCEPTION));

        return tokenService.generateToken(user.getId(), applicationProperties.getRefTokenExpCount());
    }


    public TokenResponse signInWithGoogle(String token) {
        return null;
    }

    public TokenResponse signInWithFacebook(String token) {
        return null;
    }

    public TokenResponse signInWithX(String token) {
        return null;
    }

    public TokenResponse signInWithLinkedin(String token) {
        return null;
    }
}
