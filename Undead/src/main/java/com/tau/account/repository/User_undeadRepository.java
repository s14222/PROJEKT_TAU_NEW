package com.tau.account.repository;

import com.tau.account.model.User_undead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface User_undeadRepository extends JpaRepository<User_undead, Long> {

    User_undead save(User_undead user_undead);

    User_undead findByUndeadId(Long id);

    List<User_undead> findByUserId(Long userId);

}
