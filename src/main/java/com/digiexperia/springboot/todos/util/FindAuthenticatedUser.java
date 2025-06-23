package com.digiexperia.springboot.todos.util;

import com.digiexperia.springboot.todos.entity.User;

public interface FindAuthenticatedUser {
    User getAuthenticatedUser();
}

