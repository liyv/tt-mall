package com.liyv.taotao.web.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("backend")
public class MenuController {


    //
    @GetMapping("/{pageUrl}")
    public String menuItemRole(@PathVariable String pageUrl) {
        return "backend/" + pageUrl;
    }

}
