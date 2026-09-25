package com.example.app.service;

import com.example.app.dto.SignupForm;
import com.example.app.entity.User;

import java.util.List;

public interface UserService {
    User registerNewUser(SignupForm form);
    long countUsers();
    List<User> findAll();
}
