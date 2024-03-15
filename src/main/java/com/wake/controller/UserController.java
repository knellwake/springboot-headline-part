package com.wake.controller;

import com.wake.pojo.User;
import com.wake.service.UserService;
import com.wake.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public Result login(@RequestBody User user){

        Result result = userService.login(user);

        return result;
    }


}