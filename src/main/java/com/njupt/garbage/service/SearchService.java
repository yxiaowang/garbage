package com.njupt.garbage.service;

import com.njupt.garbage.common.pojo.EUDataGridResult;
import com.njupt.garbage.pojo.GarbageItem;

import java.sql.*;
import java.util.List;

public interface SearchService {


     List<GarbageItem> findGarbageByCat(String name)throws ClassNotFoundException, SQLException;

}
