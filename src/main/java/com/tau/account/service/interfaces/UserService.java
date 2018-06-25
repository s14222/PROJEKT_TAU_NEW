package com.tau.account.service.interfaces;

import com.tau.account.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

   // Long addUser(User user);
    List<User> findAll();
    void delete(User user);
    void update(User user);

    User findById(Long id);

    void saveUser(User u);
}
