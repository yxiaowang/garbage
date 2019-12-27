package com.njupt.garbage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njupt.garbage.common.pojo.EUDataGridResult;
import com.njupt.garbage.mapper.GarbageLabelMapper;
import com.njupt.garbage.pojo.GarbageItem;
import com.njupt.garbage.pojo.GarbageLabel;
import com.njupt.garbage.pojo.GarbageLabelExample;
import com.njupt.garbage.service.LabelService;
import com.njupt.garbage.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.PooledConnection;
import java.sql.*;
import java.util.List;

@Service
public abstract class SearchServiceImpl implements SearchService {
    @Autowired
    private GarbageLabelMapper garbageLabelMapper;
    @Override
    public List<GarbageItem> findGarbageByCat(String name) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/garbage?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root");
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT item_name FROM gg_item,gg_item_cat ");
        sql.append(" WHERE  gg_item_cat.name_cn = ");
        sql.append(name);
        sql.append(" AND gg_item.cid = gg_item_cat.id");
        Statement statement = conn.createStatement();
        ResultSet res = statement.executeQuery(sql.toString());
        res.next();
        System.out.println(res.getString("item_name"));
//        return null;
        return (List<GarbageItem>) res;
    }

}
