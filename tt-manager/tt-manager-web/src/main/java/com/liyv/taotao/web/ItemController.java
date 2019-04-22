package com.liyv.taotao.web;

import com.liyv.taotao.entity.TaoItem;
import com.liyv.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/{itemId}")
    @ResponseBody
    public TaoItem getItemById(@PathVariable long itemId) {
        return itemService.getItemById(itemId);
    }
}
