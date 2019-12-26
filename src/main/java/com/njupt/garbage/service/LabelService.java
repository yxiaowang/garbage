package com.njupt.garbage.service;

import com.njupt.garbage.common.pojo.EUDataGridResult;

public interface LabelService {


    EUDataGridResult findLabelList(int page, int rows) throws Exception;

}
