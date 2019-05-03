package com.liyv.portal.controller;

import com.liyv.portal.pojo.TaoItem;
import com.liyv.portal.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    public String showItemInfo(@PathVariable long itemId, Model model) {
        TaoItem item = itemService.getItemById(itemId);
        if (null != item) {
            model.addAttribute("item", item);
            return "item";
        }
        return "error/exception";

    }
}
