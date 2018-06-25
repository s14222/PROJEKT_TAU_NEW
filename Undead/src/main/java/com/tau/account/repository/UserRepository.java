package com.tau.account.repository;

import com.tau.account.model.Undead;
import com.tau.account.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findAll();
    void delete(User user);

    List<Undead> findByUndeadList(User user);

    User findById(Long id);


}
