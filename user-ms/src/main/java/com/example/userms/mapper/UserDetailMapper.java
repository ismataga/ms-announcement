package com.example.userms.mapper;


import com.example.userms.entity.User;
import com.example.userms.model.request.UserInfoClientRequest;

public enum UserDetailMapper {
    USER_DETAIL_MAPPER;

    public User mapToEntity(UserInfoClientRequest userInfoClientRequest) {
        return User.builder()
                .id(userInfoClientRequest.userId())
                .username(userInfoClientRequest.username())
                .fullName(userInfoClientRequest.fullName())
                .build();
    }

}
