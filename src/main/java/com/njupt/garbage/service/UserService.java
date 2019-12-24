package com.njupt.garbage.service;

import com.njupt.garbage.common.pojo.Result;
import com.njupt.garbage.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {

    Result login(String username, String password, HttpServletRequest request, HttpServletResponse response);
    Result registerUser(User tbUser);

}
