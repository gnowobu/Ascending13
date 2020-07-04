package com.tzy.service;

import com.tzy.model.User;
import com.tzy.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> getAllUsers(){
        return userDao.findAllUsers();
    }

    public User getByName(String name){return userDao.getByName(name);}

    public User getByCredentials(String username, String password){return userDao.getUserByCredentials(username, password);}
}
