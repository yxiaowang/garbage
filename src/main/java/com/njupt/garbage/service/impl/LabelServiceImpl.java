package com.njupt.garbage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njupt.garbage.common.pojo.EUDataGridResult;
import com.njupt.garbage.mapper.GarbageLabelMapper;
import com.njupt.garbage.pojo.*;
import com.njupt.garbage.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelServiceImpl implements LabelService{
    @Autowired
    private GarbageLabelMapper garbageLabelMapper;

    public EUDataGridResult findLabelList(int page,int rows) throws Exception{
        GarbageLabelExample example = new GarbageLabelExample();
        // 设置分页
        PageHelper.startPage(page, rows);
        List<GarbageLabel> list = garbageLabelMapper.selectByExample(example);
        PageInfo<GarbageLabel> pageInfo = new PageInfo<GarbageLabel>(list);
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        euDataGridResult.setRows(list);
        euDataGridResult.setTotal(pageInfo.getTotal());
        return euDataGridResult;
    }

    public EUDataGridResult findItemList(int page, int rows) throws Exception {
        return null;
    }
}
