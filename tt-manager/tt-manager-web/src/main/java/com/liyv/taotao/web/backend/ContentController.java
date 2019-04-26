package com.liyv.taotao.web.backend;

import com.liyv.taotao.dto.Result;
import com.liyv.taotao.dto.TaoItemCatDTO;
import com.liyv.taotao.entity.ContentCategoryEntity;
import com.liyv.taotao.service.TaoContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("content")
public class ContentController {

    @Autowired
    TaoContentCategoryService contentCategoryService;

    @GetMapping("/categoryList")
    @ResponseBody
    public Object listContentCategoryByPid(@RequestParam(value = "id", defaultValue = "0") long pid) {
        List<TaoItemCatDTO> list = contentCategoryService.listContentCategoryByPid(pid);
//        return new Result<>(true, list);
        return list;
    }

    @PostMapping("/category/create")
    @ResponseBody
    public Result saveContentCategory(@RequestParam("parentId") long parentId, @RequestParam("name") String name) {
        ContentCategoryEntity entity = new ContentCategoryEntity();
        entity.setName(name);
        byte isParent = 0;
        entity.setIsParent(isParent);
        entity.setParentId(parentId);
        entity.setSortOrder(1);
        byte status = 1;
        entity.setStatus(status);
        entity.setCreated(new Date());
        entity.setUpdated(new Date());
        TaoItemCatDTO dto = contentCategoryService.saveContentCategory(entity);
        return new Result<>(true, dto);
    }

    @PostMapping("/category/update")
    @ResponseBody
    public Result updateContentCategory(@RequestParam("id") long id, @RequestParam("name") String name) {
        int row = contentCategoryService.updateCategory(id, name);
        if (row > 0) {
            return new Result<>(true, 1);
        } else {
            return new Result<>(false, "更新失败!");
        }
    }

    @PostMapping("/category/{id}/delete")
    @ResponseBody
    public Result deleteCategory(@PathVariable("id") long id) {
        int row = contentCategoryService.deleteCategory(id);
        if (row > 0) {
            return new Result<>(true, row);
        } else {
            return new Result<>(false, "删除失败!");
        }
    }
}
