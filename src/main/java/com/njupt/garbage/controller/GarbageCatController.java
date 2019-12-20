package com.njupt.garbage.controller;

import com.njupt.garbage.common.pojo.EUTreeNode;
import com.njupt.garbage.service.GarbageCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class GarbageCatController {

    @Autowired
    private GarbageCatService garbageCatService;

    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List<EUTreeNode> findItemCat(@RequestParam(value = "id", defaultValue = "0") Long parent_id){
        List<EUTreeNode> list = garbageCatService.findCat(parent_id);
        return list;
    }
}
