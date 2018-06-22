package com.tau.account.service.interfaces;

import com.tau.account.model.Undead;
import com.tau.account.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

   // Long addUser(User user);
    List<User> findAll();
    void delete(User user);
    User findByPesel(String pesel);
    void update(User user);
    //List<Undead> findByUndeadList(User user);

    User findById(Long id);
}
