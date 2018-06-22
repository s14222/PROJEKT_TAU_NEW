package com.tau.account.service;

import com.tau.account.model.Undead;
import com.tau.account.model.User;
import com.tau.account.repository.UserRepository;
import com.tau.account.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {

        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);

    }

    @Override
    public User findByPesel(String pesel) {
        return userRepository.findByPesel(pesel);
    }

    @Override
    public void update(User user) {

        userRepository.save(user);

    }

//    @Override
//    public List<Undead> findByUndeadList(User user) {
//        return userRepository.findByUndeadList(user);
//    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }
}
