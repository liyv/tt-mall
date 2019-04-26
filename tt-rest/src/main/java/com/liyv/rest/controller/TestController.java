package com.liyv.rest.controller;

import com.liyv.rest.pojo.CatResult;
import com.liyv.rest.service.ItemCatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    ItemCatService itemCatService;

    @GetMapping("/rest")
    public CatResult f() {
        return itemCatService.getItemCatList();
    }
}
