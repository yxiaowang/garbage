package com.njupt.garbage.controller;


import com.njupt.garbage.common.pojo.EUDataGridResult;
import com.njupt.garbage.common.pojo.Result;
import com.njupt.garbage.pojo.GarbageItem;
import com.njupt.garbage.service.GarbageItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class GarbageItemController {

    @Autowired
    private GarbageItemService garbageItemService;

    @RequestMapping("/item/save")
    @ResponseBody
    public Result addGarbageItem(String image, GarbageItem garbageItem){
        Result result = garbageItemService.addGarbageItem(image, garbageItem);
        return result;
    }

    @RequestMapping("/item/list")
    @ResponseBody
    public EUDataGridResult findItemList(Integer page, Integer rows) throws Exception {
        //构造返回对象，返回json数据即可
        EUDataGridResult euDataGridResult = garbageItemService.findItemList(page, rows);
        return euDataGridResult;
    }

    @RequestMapping("/item/update")
    @ResponseBody
    public Result updateItem(String image, GarbageItem item){
        Result result = garbageItemService.updateItem(image, item);
        return result;
    }

    @RequestMapping("/item/delete")
    @ResponseBody
    public Result deleteById(String ids){
        String[] idss = ids.split(",");
        for (String id: idss){
            garbageItemService.deleteById(Long.parseLong(id));
        }
        return Result.ok();
    }

    @RequestMapping("/item/findById/{id}")
    @ResponseBody
    public GarbageItem findGarbageItemById(@PathVariable long id){
        GarbageItem garbageItem = garbageItemService.findGarbageItemById(id);
        return garbageItem;
    }

    @RequestMapping("/item/findByName/{name}")
    @ResponseBody
    public List<GarbageItem> findGabageItemsByName(@PathVariable String name){
        List<GarbageItem> list = garbageItemService.findGarbageByName(name);
        return list;
    }

    @RequestMapping("/item/deleteByName/{name}")
    @ResponseBody
    public Result deleteByName(@PathVariable String name){
        Result result = garbageItemService.deleteByItemName(name);
        return result;
    }
}
