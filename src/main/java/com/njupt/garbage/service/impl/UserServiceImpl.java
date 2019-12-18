package com.njupt.garbage.service.impl;

import com.njupt.garbage.common.pojo.Result;
import com.njupt.garbage.mapper.UserMapper;
import com.njupt.garbage.pojo.User;
import com.njupt.garbage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserById(int id) {
        if (id < 0) return null;
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }
}
