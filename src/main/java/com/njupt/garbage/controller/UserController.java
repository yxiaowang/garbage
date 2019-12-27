package com.njupt.garbage.controller;


import com.njupt.garbage.anotation.OnlyManager;
import com.njupt.garbage.anotation.OnlyRoot;
import com.njupt.garbage.common.pojo.EUDataGridResult;
import com.njupt.garbage.common.pojo.Result;
import com.njupt.garbage.common.utils.ExceptionUtil;
import com.njupt.garbage.pojo.User;
import com.njupt.garbage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(String username, String password, HttpServletRequest request, HttpServletResponse response){
        try {
            Result result = userService.login(username, password, request, response);
            return result;
        }catch (Exception e){
            return Result.build(400, ExceptionUtil.getStackTrace(e));
        }
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    @ResponseBody
    public Result register(User user){
        try {
            Result result = userService.registerUser(user);
            return result;
        }catch (Exception e){
            return Result.build(400, ExceptionUtil.getStackTrace(e));
        }
    }

    @RequestMapping("/user/list")
    @ResponseBody
    @OnlyRoot
    public EUDataGridResult findItemList(Integer page, Integer rows) throws Exception {
        //构造返回对象，返回json数据即可
        EUDataGridResult euDataGridResult = userService.findUserList(page, rows);
        return euDataGridResult;
    }

    @RequestMapping("/user/setPermission")
    @ResponseBody
    @OnlyRoot
    public Result setPermission(String ids, String type){
        String[] idss = ids.split(",");
        for (String id: idss){
            userService.setPermission(Long.parseLong(id), Integer.parseInt(type));
        }
        return Result.ok();
    }
}
