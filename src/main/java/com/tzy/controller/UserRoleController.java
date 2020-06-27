package com.tzy.controller;

import com.tzy.model.Role;
import com.tzy.model.User;
import com.tzy.service.RoleService;
import com.tzy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> findAllUsers() {

        return userService.getAllUsers();
    }

    @GetMapping(value = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Role> findAllRoles() {
        return roleService.getRoles();
    }

}
