package com.njupt.garbage.service;

import com.njupt.garbage.common.pojo.EUTreeNode;

import java.util.List;

public interface GarbageCatService {
    List<EUTreeNode> findCat(long parent_id);

}
