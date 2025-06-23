package com.digiexperia.springboot.todos.service;

import com.digiexperia.springboot.todos.request.PasswordUpdateRequest;
import com.digiexperia.springboot.todos.response.UserResponse;

public interface UserService {
    UserResponse getUserInfo();
    void deleteUser();
    void updatePassword(PasswordUpdateRequest passwordUpdateRequest);
}

