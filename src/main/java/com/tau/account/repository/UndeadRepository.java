package com.tau.account.repository;

import com.tau.account.model.Undead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UndeadRepository extends JpaRepository<Undead, Long> {
    Undead save(Undead undead);

    void delete(Undead undead);

    //void update(Undead undead);

    List<Undead> findAll();

    List<Undead> findByUserName(String username);

    Undead findById(Long id);
}