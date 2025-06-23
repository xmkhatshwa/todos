package com.digiexperia.springboot.todos.response;

import com.digiexperia.springboot.todos.entity.Authority;
import lombok.Data;

import java.util.List;

@Data
public class UserResponse {

    private long id;

    private String fullName;

    private String email;

    private List<Authority> authorities;

    public UserResponse(long id, String fullName, String email, List<Authority> authorities) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.authorities = authorities;
    }

}