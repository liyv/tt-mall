package com.liyv.taotao.web;

import com.liyv.taotao.entity.TaoItem;
import com.liyv.taotao.dto.ItemListDTO;
import com.liyv.taotao.dto.Result;
import com.liyv.taotao.entity.TaoItemCatEntity;
import com.liyv.taotao.service.ItemService;
import com.liyv.taotao.service.TaoItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private TaoItemCatService catService;

    @RequestMapping("/{itemId}")
    @ResponseBody
    public TaoItem getItemById(@PathVariable long itemId) {
        return itemService.getItemById(itemId);
    }

    @RequestMapping("/list")
    @ResponseBody
    public Result listItem(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "1") int rows) {
        ItemListDTO dto = itemService.listGoods(page, rows);
        Result<ItemListDTO> result = new Result<>(true, dto);
        return result;
    }

    @GetMapping("/listCategory")
    @ResponseBody
    public Result listItemCategory() {
        List<TaoItemCatEntity> list = catService.listCategory();
        return new Result<>(true, list);
    }
}
