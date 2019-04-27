package com.liyv.portal.controller;

import com.liyv.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    ContentService contentService;

    @RequestMapping("/index")
    public String showIndex(Model model) {
        //取大广告位内容
        String data = contentService.getAdList();
        model.addAttribute("ad1", data);
        return "index";
    }
}
