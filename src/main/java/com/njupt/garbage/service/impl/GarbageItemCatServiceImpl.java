package com.njupt.garbage.service.impl;

import com.njupt.garbage.common.pojo.EUTreeNode;
import com.njupt.garbage.common.pojo.Result;
import com.njupt.garbage.mapper.GarbageCategoryMapper;
import com.njupt.garbage.pojo.GarbageCategory;
import com.njupt.garbage.pojo.GarbageCategoryExample;
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
    public Result addCat(Long cid, GarbageCategory garbageCategory) {
        garbageCategory.setParentId(cid);
        // 首先更新其父目录的isParent属性
        GarbageCategory father = new GarbageCategory();
        father.setId(garbageCategory.getParentId());
        father.setIsParent(true);
        garbageCategoryMapper.updateByPrimaryKeySelective(father);
        // 其次添加该category
        garbageCategory.setIsParent(false);
        Date date = new Date();
        garbageCategory.setCreated(date);
        garbageCategory.setCatStatus(1);
        garbageCategory.setUpdated(date);
        garbageCategoryMapper.insert(garbageCategory);
        return Result.ok();
    }

    @Override
    public Result updateCat(GarbageCategory garbageCategory) {
        return null;
    }
}
