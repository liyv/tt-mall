package com.liyv.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liyv.rest.pojo.CatResult;
import com.liyv.rest.service.ItemCatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemCatController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ItemCatService itemCatService;

    //
    @GetMapping(value = "/itemcat/list",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getItemCatListJson(String callback) {
        CatResult catResult = itemCatService.getItemCatList();
        //把pojo转换成字符串
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try {
            json = objectMapper.writeValueAsString(catResult);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        String result = callback + "(" + json + ");";
        return result;
    }
}
