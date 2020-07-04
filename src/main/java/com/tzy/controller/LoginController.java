package com.tzy.controller;

import com.tzy.model.User;
import com.tzy.service.JWTService;
import com.tzy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



//1.validate user exists in db
//2.create JWToken
//3.return token
@RestController
@RequestMapping(value = "/auth")
public class LoginController {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String authentication(@RequestParam("username") String username, @RequestParam("password") String password){

        User user = new User();
        user.setName(username);
        user.setPassword(password);
        if(userService.getByCredentials(username, password) != null) {
            String token = jwtService.generateToken(user);
            logger.info("token has been created successfully");
            return token;
        }

        else return null;

    }


}
