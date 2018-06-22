package com.tau.account.service;

import com.tau.account.model.Undead;
import com.tau.account.model.User;
import com.tau.account.repository.UndeadRepository;
import com.tau.account.service.interfaces.UndeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UndeadServiceImpl implements UndeadService {

    @Autowired
    private UndeadRepository undeadRepository;


    @Override
    public void save(Undead undead) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails) authentication.getPrincipal(); //pobiera zalogownego uzytk

        String username = user.getUsername();
        undead.setUserName(username);
        undeadRepository.save(undead);
    }

    /*@Override
    public void saveWithUserName(Undead undead, String userName){
        undead.setUserName(userName);
        undeadRepository.save(undead);
    }*/

    @Override
    public void disposeUndead(User user, Undead undead) {
        //dispose
        if(user.getUsername().equals(undead.getUserName())){
            undead.setUserName("");
        }
    }

    @Override
    public void delete(Undead undead) {

        undeadRepository.delete(undead);

    }


    @Override
    public List<Undead> findAll() {

        return undeadRepository.findAll();

    }

    @Override
    public Undead findByName(String name) {
        return undeadRepository.findByName(name);
    }


    @Override
    public List<Undead> findByUserName(String username) {
        return undeadRepository.findByUserName(username);
    }

    @Override
    public Undead findById(Long Id) {
        return undeadRepository.findById(Id);
    }

    @Override
    public List<Undead> findByMale(Undead undeadList) {
        return undeadRepository.findByMale(undeadList);
    }
}
