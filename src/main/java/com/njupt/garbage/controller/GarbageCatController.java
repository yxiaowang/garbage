package com.njupt.garbage.controller;

import com.njupt.garbage.anotation.OnlyManager;
import com.njupt.garbage.common.pojo.EUDataGridResult;
import com.njupt.garbage.common.pojo.EUTreeNode;
import com.njupt.garbage.common.pojo.Result;
import com.njupt.garbage.pojo.GarbageCategory;
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

    @RequestMapping("/cat/save")
    @ResponseBody
    @OnlyManager
    public Result addCat(String image, Long cid, GarbageCategory garbageCategory){
        Result result = garbageCatService.addCat(image, cid, garbageCategory);
        return result;
    }

    @RequestMapping("/cat/list")
    @ResponseBody
    public EUDataGridResult findCatList(Integer page, Integer rows) throws Exception{
        EUDataGridResult result = garbageCatService.findCatList(page, rows);
        return result;
    }

    @RequestMapping("/cat/update")
    @ResponseBody
    @OnlyManager
    public Result updateCat(String image, Long cid, GarbageCategory garbageCategory){
        Result result = garbageCatService.updateCat(image, cid, garbageCategory);
        return result;
    }

    @RequestMapping("/cat/delete")
    @ResponseBody
    @OnlyManager
    public Result deleteCatById(String ids){
        String[] idss = ids.split(",");
        for (String id: idss){
            garbageCatService.deleteCatById(Long.parseLong(id));
        }
        return Result.ok();
    }

}
