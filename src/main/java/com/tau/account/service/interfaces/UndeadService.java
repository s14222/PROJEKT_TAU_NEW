package com.tau.account.service.interfaces;

import com.tau.account.model.Undead;

import java.util.List;

public interface UndeadService {

    void save(Undead undead);

    void delete(Undead undead);

    List<Undead> findAll();

    Undead findByName(String name);

    List<Undead> findByUserName(String username);

    Undead findById(Long Id);
}
