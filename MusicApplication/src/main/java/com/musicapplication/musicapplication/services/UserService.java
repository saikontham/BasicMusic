package com.musicapplication.musicapplication.services;

import com.musicapplication.musicapplication.entities.User;

public interface UserService {




    public void addUsers(User user);

    public boolean isEmailExists(String email);

    public String validateRole(String email);

    public boolean validateLogin(String password,String email);

    public boolean resetPassword(String email, String oldPassword, String newPassword);
    public User getUser(String email);
    public void updateUser(User user);
}
