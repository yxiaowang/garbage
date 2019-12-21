package com.njupt.garbage.service;

import com.njupt.garbage.common.pojo.EUDataGridResult;
import com.njupt.garbage.common.pojo.EUTreeNode;
import com.njupt.garbage.common.pojo.Result;
import com.njupt.garbage.pojo.GarbageCategory;

import java.util.List;

public interface GarbageCatService {
    List<EUTreeNode> findCat(long parent_id);
    Result addCat(String image, Long cid, GarbageCategory garbageCategory);
    Result updateCat(String image, Long cid, GarbageCategory garbageCategory);
    EUDataGridResult findCatList(int page, int rows) throws Exception;
    Result deleteCatById(Long id);
}
