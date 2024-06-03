package com.example.userms.service;

import com.example.userms.entity.User;
import com.example.userms.exception.AppException;
import com.example.userms.model.request.UserInfoClientRequest;
import com.example.userms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.userms.mapper.UserDetailMapper.USER_DETAIL_MAPPER;
import static com.example.userms.model.constants.ExceptionConstants.USERNAME_EXIST_EXCEPTION;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

     public void createUser(UserInfoClientRequest userInfoClientRequest) {

        if (userRepository.findByUsername(userInfoClientRequest.username()).isPresent()) {
            log.info("User already exists");
            throw new AppException(USERNAME_EXIST_EXCEPTION);
        }

        User user = USER_DETAIL_MAPPER.mapToEntity(userInfoClientRequest);
         userRepository.save(user);
    }

}
