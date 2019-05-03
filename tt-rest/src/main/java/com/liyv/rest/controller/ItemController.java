package com.liyv.rest.controller;

import com.liyv.rest.service.ItemService;
import com.liyv.taotao.dto.Result;
import com.liyv.taotao.entity.TaoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 查询商品基本信息
     * @param itemId
     * @return
     */
    @GetMapping("/base/{itemId}")
    @ResponseBody
    public Result getItemById(@PathVariable long itemId) {
        try {
            TaoItem item = itemService.getItemByID(itemId);
            return new Result<>(true, item);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(false, e.getMessage());
        }
    }
}
