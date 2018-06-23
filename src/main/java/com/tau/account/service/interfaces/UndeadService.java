package com.tau.account.service.interfaces;

import com.tau.account.model.Undead;
import com.tau.account.model.User;

import java.util.List;

public interface UndeadService {

    void save(Undead undead);

    void delete(Undead undead);

    List<Undead> findAll();

    Undead findByName(String name);

    List<Undead> findByUserName(String username);

    Undead findById(Long Id);

    //List<Undead> getMaleUndeads();

   // void saveWithUserName(Undead undead, String userName);


   // void assignedUndead(Long userId, Long undeadId);
  //  void disposeUndead(User user, Undead undead);
    void disposeUndead(User user, Undead undead);

}
