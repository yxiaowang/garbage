package com.njupt.garbage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njupt.garbage.common.pojo.EUDataGridResult;
import com.njupt.garbage.common.pojo.Result;
import com.njupt.garbage.common.utils.CookieUtils;
import com.njupt.garbage.mapper.UserMapper;
import com.njupt.garbage.pojo.GarbageItem;
import com.njupt.garbage.pojo.GarbageItemExample;
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

    @Value("${COOKIE_ID}")
    private String COOKIE_ID;
    @Value("${COOKIE_IDENTITY}")
    private String COOKIE_IDENTITY;

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
        //设置用户id
        CookieUtils.setCookie(request, response, COOKIE_ID, user.getId() + "");
        //存入用户身份
        CookieUtils.setCookie(request, response, COOKIE_IDENTITY, user.getType() + "");
        return Result.ok();
    }

    @Override
    public Result registerUser(User user) {
        // 查看用户名是否已经被注册
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(user.getUsername());
        List<User> list = userMapper.selectByExample(example);
        if (list != null && list.size() > 0)
            return Result.build(400, "该用户名已经被注册！", false);
        Date date = new Date();
        user.setCreated(date);
        user.setUpdated(date);
        user.setPasswd(DigestUtils.md5DigestAsHex(user.getPasswd().getBytes()));
        user.setType(1);
        userMapper.insert(user);
        return Result.build(200, "OK", true);
    }

    @Override
    public EUDataGridResult findUserList(int page, int rows) throws Exception {
        UserExample example = new UserExample();
        // 设置分页
        PageHelper.startPage(page, rows);
        List<User> list = userMapper.selectByExample(example);

        PageInfo<User> pageInfo = new PageInfo<User>(list);
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        euDataGridResult.setRows(list);
        euDataGridResult.setTotal(pageInfo.getTotal());
        return euDataGridResult;
    }

    @Override
    public Result setPermission(long id, int type) {
        Date date = new Date();
        User user = new User();
        user.setType(type);
        user.setCreated(date);
        user.setUpdated(date);
        user.setId(id);
        userMapper.updateByPrimaryKeySelective(user);
        return Result.ok();
    }
}
