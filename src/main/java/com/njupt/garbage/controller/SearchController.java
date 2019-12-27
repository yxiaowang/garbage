package com.njupt.garbage.controller;

import com.njupt.garbage.common.pojo.EUDataGridResult;
import com.njupt.garbage.pojo.GarbageItem;
import com.njupt.garbage.service.LabelService;
import com.njupt.garbage.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping("/search/list")
    @ResponseBody
    public EUDataGridResult findGarbageByCat(String name) throws Exception {
        //构造返回对象，返回json数据即可
        List<GarbageItem> euDataGridResult = searchService.findGarbageByCat(name);
        return (EUDataGridResult) euDataGridResult;
    }
}
