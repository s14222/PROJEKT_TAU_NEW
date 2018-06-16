package com.tau.account.service;

import com.tau.account.model.Undead;
import com.tau.account.repository.UndeadRepository;
import com.tau.account.service.interfaces.UndeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UndeadServiceImpl implements UndeadService {

    @Autowired
    private UndeadRepository undeadRepository;


    @Override
    public void save(Undead undead) {

        undeadRepository.save(undead);

    }

    @Override
    public void delete(Undead undead) {

        undeadRepository.delete(undead);

    }

    @Override
    public void update(Undead undead) {
        undeadRepository.save(undead);
    }

    @Override
    public List<Undead> findAll() {

        return undeadRepository.findAll();

    }

    @Override
    public Undead findByName(String name) {
        return null;
    }


    @Override
    public List<Undead> findByUserName(String username) {
        return undeadRepository.findByUserName(username);
    }

    @Override
    public Undead findById(Long Id) {
        return undeadRepository.findById(Id);
    }
}
