package com.digiexperia.springboot.todos.service;

import com.digiexperia.springboot.todos.request.AuthenticationRequest;
import com.digiexperia.springboot.todos.request.RegisterRequest;
import com.digiexperia.springboot.todos.response.AuthenticationResponse;

public interface AuthenticationService {
    void register(RegisterRequest input) throws Exception;
    AuthenticationResponse login(AuthenticationRequest request);
}
