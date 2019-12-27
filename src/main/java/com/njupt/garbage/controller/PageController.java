package com.njupt.garbage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/main")
    public String showIndex(){
        return "main";
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

    @RequestMapping("/catAdd")
    public String showCatAddPage(){
        return "catAdd";
    }

    @RequestMapping("/cat-list")
    public String showCatListPage(){
        return "cat-list";
    }

    @RequestMapping("/cat-edit")
    public String showCatEditPage(){
        return "cat-edit";
    }

    @RequestMapping("/user/register")
    public String showRegisterPage(){
        return "register";
    }

    @RequestMapping("/user/login")
    public String showLoginPage(String redirect, Model model){
        model.addAttribute("redirect", redirect);
        return "index";
    }

    @RequestMapping("/search")
    public String showSearchPage(){
        return "search";
    }

    @RequestMapping("/label-list")
    public String showLabelListPage(){
        return "label-list";
    }

    @RequestMapping("/label-add")
    public String showLabelAddPage(){
        return "label-add";
    }

    @RequestMapping("/label-edit")
    public String showLabelEditPage(){
        return "label-edit";
    }

    @RequestMapping("/user-list")
    public String showUserListPage(){
        return "user-list";
    }
}
