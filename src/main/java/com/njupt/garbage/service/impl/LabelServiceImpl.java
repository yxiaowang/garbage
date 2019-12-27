package com.njupt.garbage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.njupt.garbage.common.pojo.EUDataGridResult;
import com.njupt.garbage.common.pojo.Result;
import com.njupt.garbage.common.utils.IDUtils;
import com.njupt.garbage.mapper.GarbageLabelMapper;
import com.njupt.garbage.pojo.GarbageItem;
import com.njupt.garbage.pojo.GarbageItemExample;
import com.njupt.garbage.pojo.GarbageLabel;
import com.njupt.garbage.pojo.GarbageLabelExample;
import com.njupt.garbage.service.LabelService;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    private GarbageLabelMapper garbageLabelMapper;

    @Override
    public EUDataGridResult findLabelList(int page, int rows) throws Exception {
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

    @Override
    public Result addLabel(GarbageLabel label) {
        label.setId(IDUtils.genItemId());
        //设置创建和更新时间为当前时间
        Date date = new Date();
        label.setLabelStatus(1);
        label.setCreateTime(date);
        label.setUpdateTime(date);
        garbageLabelMapper.insert(label);
        return Result.ok();
    }

    @Override
    public Result deleteById(long id) {
        garbageLabelMapper.deleteByPrimaryKey(id);
        return Result.ok();
    }

    @Override
    public Result updateItem(GarbageLabel label) {
        Date date = new Date();
        label.setUpdateTime(date);
        label.setCreateTime(date);
        garbageLabelMapper.updateByPrimaryKeySelective(label);
        return Result.ok();
    }
}
