package com.tau.account.service;

import com.tau.account.model.Undead;
import com.tau.account.model.User;
import com.tau.account.model.User_undead;
import com.tau.account.repository.User_undeadRepository;
import com.tau.account.service.interfaces.UndeadService;
import com.tau.account.service.interfaces.User_undeadService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class User_undeadServiceImpl implements User_undeadService {

    @Autowired
    private User_undeadRepository user_undeadRepository;

    @Autowired
    private UndeadService undeadService;


    @Override
    public User_undead save(User_undead user_undead) {
        return user_undeadRepository.save(user_undead);
    }

    @Override
    public User_undead findById(Long id) {
        return user_undeadRepository.findByUndeadId(id);
    }

    @Override
    public List<User_undead> findUser_undeadBy(Long userId) {
        return user_undeadRepository.findByUserId(userId);
    }

    @Override
    public void dispose(Long userId, Long undeadId) {

        Undead undeadToDispose = undeadService.findById(undeadId);
        if(undeadToDispose!= null){

            undeadService.delete(undeadToDispose);


        }

        /*User_undead user_undeadToDispose = user_undeadRepository.findByUndeadId(undeadId);

        if(user_undeadToDispose!= null && undeadToDispose!= null){
            user_undeadToDispose.setUserId(0L);
            user_undeadRepository.save(user_undeadToDispose);

        }*/


    }


}
