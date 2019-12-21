package com.njupt.garbage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njupt.garbage.common.pojo.EUDataGridResult;
import com.njupt.garbage.common.pojo.EUTreeNode;
import com.njupt.garbage.common.pojo.Result;
import com.njupt.garbage.mapper.GarbageCategoryMapper;
import com.njupt.garbage.pojo.GarbageCategory;
import com.njupt.garbage.pojo.GarbageCategoryExample;
import com.njupt.garbage.pojo.GarbageItem;
import com.njupt.garbage.pojo.GarbageItemExample;
import com.njupt.garbage.service.GarbageCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GarbageItemCatServiceImpl implements GarbageCatService {

    @Autowired
    private GarbageCategoryMapper garbageCategoryMapper;

    @Override
    public List<EUTreeNode> findCat(long parent_id) {
        /*
        此方法实现查询垃圾项的分类，
        传入：parent_id
        功能：根据parent_id来查询子分类，并按照TreeNode的形式返回
        返回：返回一个TreeNode的list，在controller端以json数据格式返回
        */
        GarbageCategoryExample example = new GarbageCategoryExample();
        GarbageCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parent_id);
        List<GarbageCategory> list = garbageCategoryMapper.selectByExample(example);
        //新建一个TreeNode的list
        List<EUTreeNode> treeNodes = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (GarbageCategory garbageCategory : list) {
                EUTreeNode treeNode = new EUTreeNode();
                treeNode.setId(garbageCategory.getId());
                treeNode.setText(garbageCategory.getNameCn());
                treeNode.setState(garbageCategory.getIsParent() ? "closed" : "open");
                treeNodes.add(treeNode);
            }
        }
        return treeNodes;
    }

    @Override
    public Result addCat(String image, Long cid, GarbageCategory garbageCategory) {
        if (cid != null){
            garbageCategory.setParentId(cid);
            // 首先更新其父目录的isParent属性
            GarbageCategory father = new GarbageCategory();
            father.setId(garbageCategory.getParentId());
            father.setIsParent(true);
            garbageCategoryMapper.updateByPrimaryKeySelective(father);
        }else{
            garbageCategory.setParentId((long)0);
        }
        // 其次添加该category
        garbageCategory.setIsParent(false);
        Date date = new Date();
        garbageCategory.setLogo(image);
        garbageCategory.setCreated(date);
        garbageCategory.setCatStatus(1);
        garbageCategory.setUpdated(date);
        garbageCategoryMapper.insert(garbageCategory);
        return Result.ok();
    }

    @Override
    public EUDataGridResult findCatList(int page, int rows) throws Exception {
        GarbageCategoryExample example = new GarbageCategoryExample();
        GarbageCategoryExample.Criteria criteria = example.createCriteria();
        // 设置分页
        PageHelper.startPage(page, rows);
        List<GarbageCategory> list = garbageCategoryMapper.selectByExample(example);
        PageInfo<GarbageCategory> pageInfo = new PageInfo<GarbageCategory>(list);
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        euDataGridResult.setRows(list);
        euDataGridResult.setTotal(pageInfo.getTotal());
        return euDataGridResult;
    }

    @Override
    public Result updateCat(String image, Long cid, GarbageCategory garbageCategory) {
        if (cid != null){
            // 更新父目录的状态
            garbageCategory.setParentId(cid);
            GarbageCategory father = new GarbageCategory();
            father.setId(garbageCategory.getParentId());
            father.setIsParent(true);
            garbageCategoryMapper.updateByPrimaryKeySelective(father);
        }
        if (image != null){
            garbageCategory.setLogo(image);
        }
        // 其次更新该category
        Date date = new Date();
        garbageCategory.setUpdated(date);
        garbageCategoryMapper.updateByPrimaryKeySelective(garbageCategory);
        return Result.ok();
    }

    @Override
    public Result deleteCatById(Long id) {
        garbageCategoryMapper.deleteByPrimaryKey(id);
        return Result.ok();
    }
}
