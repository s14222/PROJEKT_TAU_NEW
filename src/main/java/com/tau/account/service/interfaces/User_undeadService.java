package com.tau.account.service.interfaces;

import com.tau.account.model.User_undead;

import java.util.List;

public interface User_undeadService {

    User_undead save(User_undead user_undead);

    User_undead findById(Long id);

    List<User_undead> findUser_undeadBy(Long userId);

    void dispose(Long userId, Long undeadId);
}
