package com.tau.account.service;

import com.tau.account.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
