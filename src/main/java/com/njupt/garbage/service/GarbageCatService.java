package com.njupt.garbage.service;

import com.njupt.garbage.common.pojo.EUTreeNode;
import com.njupt.garbage.common.pojo.Result;
import com.njupt.garbage.pojo.GarbageCategory;

import java.util.List;

public interface GarbageCatService {
    List<EUTreeNode> findCat(long parent_id);
    Result addCat(Long cid, GarbageCategory garbageCategory);
    Result updateCat(GarbageCategory garbageCategory);
}
