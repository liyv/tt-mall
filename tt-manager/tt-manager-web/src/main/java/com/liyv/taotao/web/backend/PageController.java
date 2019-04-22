package com.liyv.taotao.web.backend;

import com.liyv.taotao.entity.TaoMenuEntity;
import com.liyv.taotao.entity.dto.Result;
import com.liyv.taotao.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 后台管理 Controller
 */
@Controller
@RequestMapping("backend")
public class PageController {

    @Autowired
    private MenuService menuService;

    /**
     * 首页
     */
    @RequestMapping("")
    public String showIndex() {
        return "backend/index";
    }

    @RequestMapping("/menuList")
    @ResponseBody
    public Result showMenu() {
        List<TaoMenuEntity> list = menuService.listMenu();
        Result<List<TaoMenuEntity>> result = new Result<>(true, list);
        return result;
    }
}
