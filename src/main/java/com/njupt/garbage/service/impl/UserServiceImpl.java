package com.njupt.garbage.service.impl;

import com.njupt.garbage.common.pojo.Result;
import com.njupt.garbage.common.utils.CookieUtils;
import com.njupt.garbage.mapper.UserMapper;
import com.njupt.garbage.pojo.User;
import com.njupt.garbage.pojo.UserExample;
import com.njupt.garbage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Value("{GARBAGE_COOKIE}")
    private String COOKIE_NAME;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(example);
        if (users == null || users.size() == 0) {
            return Result.build(400, "用户名或密码错误");
        }

        User user = users.get(0);
        //如果密码错误
        if (!user.getPasswd().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
            return Result.build(400, "用户名或密码错误");
        }
        String token = UUID.randomUUID().toString();
        //设置cookie
        CookieUtils.setCookie(request, response, COOKIE_NAME, token);
        return Result.ok(token);
    }

    @Override
    public Result registerUser(User user) {
        Date date = new Date();
        user.setCreated(date);
        user.setUpdated(date);
        user.setPasswd(DigestUtils.md5DigestAsHex(user.getPasswd().getBytes()));
        userMapper.insert(user);
        return Result.ok();
    }
}
