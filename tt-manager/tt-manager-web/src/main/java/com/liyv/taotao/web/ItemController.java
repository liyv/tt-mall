package com.liyv.taotao.web;

import com.liyv.taotao.dto.EUDataGridDTO;
import com.liyv.taotao.dto.ItemListDTO;
import com.liyv.taotao.dto.Result;
import com.liyv.taotao.dto.TaoItemCatDTO;
import com.liyv.taotao.entity.TaoItem;
import com.liyv.taotao.entity.TaoItemParamEntity;
import com.liyv.taotao.service.ItemParamService;
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
    @Autowired
    private ItemParamService paramService;

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

    @GetMapping("/listCategoryByPid")
    @ResponseBody
    public Result listItemCategoryByPid(@RequestParam(value = "id", defaultValue = "0") long pid) {
        List<TaoItemCatDTO> list = catService.listCategoryByPid(pid);
        return new Result<>(true, list);
    }

    @PostMapping("/saveItem")
    @ResponseBody
    public Result saveItem(TaoItem item, String itemParams) {
        item.setId(System.currentTimeMillis());
        int row = itemService.insertItem(item, itemParams);
        Result result;
        if (row > 0) {
            result = new Result(true, "success");
        } else {
            result = new Result(false, "新增数据失败");
        }
        return result;
    }

    /**
     * 根据类别id 查找模板数据
     */
    @GetMapping("/param/query/itemcatid/{catId}")
    @ResponseBody
    public Result selectParamByCat(@PathVariable long catId) {
        TaoItemParamEntity entity = paramService.selectByCatId(catId);
        Result result;
        if (null != entity) {
            result = new Result<>(true, entity);
        } else {
            result = new Result<>(true, "");
        }
        return result;
    }

    /**
     * 保存类目的规格参数数据
     *
     * @param catId
     * @param paramData
     * @return
     */
    @PostMapping("/param/save/{catId}")
    @ResponseBody
    public Result saveItemParam(@PathVariable long catId, @RequestParam("paramData") String paramData) {
        TaoItemParamEntity entity = new TaoItemParamEntity();
        entity.setItemCatId(catId);
        entity.setParamData(paramData);
        int row = paramService.saveItemParam(entity);
        Result result;
        if (row > 0) {
            result = new Result(true, entity);
        } else {
            result = new Result(false, "保存数据失败");
        }
        return result;
    }

    /**
     * 类目规格参数列表数据
     *
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("/param/list")
    @ResponseBody
    public Result<EUDataGridDTO> listItemParam(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int rows) {
        EUDataGridDTO dto = paramService.listItemParm(page, rows);
        return new Result<>(true, dto);
    }

}
