package com.njupt.garbage.controller;

import com.njupt.garbage.anotation.OnlyManager;
import com.njupt.garbage.common.pojo.EUDataGridResult;
import com.njupt.garbage.common.pojo.Result;
import com.njupt.garbage.pojo.GarbageLabel;
import com.njupt.garbage.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LabelController {

    @Autowired
    private LabelService labelService;

    @RequestMapping("/label/list")
    @ResponseBody
    public EUDataGridResult findLabelList(Integer page, Integer rows) throws Exception {
        //构造返回对象，返回json数据即可
        EUDataGridResult euDataGridResult = labelService.findLabelList(page, rows);
        return euDataGridResult;
    }

    @RequestMapping("/label/save")
    @ResponseBody
    @OnlyManager
    public Result saveLabel(GarbageLabel label){
        Result result = labelService.addLabel(label);
        return result;
    }

    @RequestMapping("/label/update")
    @ResponseBody
    @OnlyManager
    public Result updateLabel(GarbageLabel label){
        Result result = labelService.updateItem(label);
        return result;
    }

    @RequestMapping("/label/delete")
    @ResponseBody
    @OnlyManager
    public Result deleteById(String ids){
        String[] idss = ids.split(",");
        for (String id: idss){
            labelService.deleteById(Long.parseLong(id));
        }
        return Result.ok();
    }
}
