package com.njupt.garbage.service;

import com.njupt.garbage.common.pojo.EUDataGridResult;
import com.njupt.garbage.common.pojo.Result;
import com.njupt.garbage.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {

    Result login(String username, String password, HttpServletRequest request, HttpServletResponse response);
    Result registerUser(User tbUser);
    EUDataGridResult findUserList(int page, int rows) throws Exception;
    Result setPermission(long id, int type);

}
