package com.liyv.search.controller;

import com.liyv.search.service.SolrItemService;
import com.liyv.taotao.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 索引库维护
 */
@RestController
@RequestMapping("/manager")
public class SolrImportController {

    @Autowired
    private SolrItemService solrItemService;

    /**
     * 导入商品数据库到数据库
     *
     * @return
     */
    @GetMapping("/importAll")
    public Result importItem() {
        Result result;
        try {
            result = solrItemService.importAllItems();
        } catch (Exception e) {
            result = new Result(false, "导入失败");
        }
        return result;
    }
}
