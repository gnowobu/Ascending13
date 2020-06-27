package com.tzy.service;

import com.tzy.model.Role;
import com.tzy.repository.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleDao roleDao;

    public List<Role> getRoles(){
        return roleDao.findAllRoles();
    }

}
