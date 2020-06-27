package com.tzy.repository;

import com.tzy.model.User;

import java.util.List;

public interface UserDao {

    List<User> findAllUsers();
}
