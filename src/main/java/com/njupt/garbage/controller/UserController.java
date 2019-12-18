package com.njupt.garbage.controller;

import com.njupt.garbage.pojo.User;
import com.njupt.garbage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user/find/{id}")
    @ResponseBody
    User findUserById(@PathVariable int id){
        User user = userService.findUserById(id);
        return user;
    }

}
