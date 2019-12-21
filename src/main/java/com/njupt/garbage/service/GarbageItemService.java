package com.njupt.garbage.service;

import com.njupt.garbage.common.pojo.EUDataGridResult;
import com.njupt.garbage.common.pojo.Result;
import com.njupt.garbage.pojo.GarbageItem;

import java.util.List;

public interface GarbageItemService {

    Result addGarbageItem(String image, GarbageItem garbageItem);
    GarbageItem findGarbageItemById(long id);
    Result deleteByItemName(String name);
    List<GarbageItem> findGarbageByName(String name);
    EUDataGridResult findItemList(int page, int rows) throws Exception;
    Result updateItem(String image, GarbageItem garbageItem);
    Result deleteById(long id);
}
