package com.njupt.garbage.service;

import com.njupt.garbage.common.pojo.EUDataGridResult;
import com.njupt.garbage.common.pojo.Result;
import com.njupt.garbage.pojo.GarbageItem;
import com.njupt.garbage.pojo.GarbageLabel;

public interface LabelService {
    EUDataGridResult findLabelList(int page, int rows) throws Exception;
    Result addLabel(GarbageLabel label);
    Result updateItem(GarbageLabel label);
    Result deleteById(long id);

}
