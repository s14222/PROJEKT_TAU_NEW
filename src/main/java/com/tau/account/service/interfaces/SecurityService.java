package com.tau.account.service.interfaces;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
