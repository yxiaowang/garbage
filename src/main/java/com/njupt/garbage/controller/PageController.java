package com.njupt.garbage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/index")
    public String showIndex(){
        return "index";
    }

    @RequestMapping("/item-list")
    public String showItemListPage(){
        return "item-list";
    }

    @RequestMapping("/item-add")
    public String showItemAddPage(){
        return "item-add";
    }

    @RequestMapping("/item-edit")
    public String showItemEditPage(){
        return "item-edit";
    }
}
