package com.liyv.search.controller;

import com.liyv.search.dto.SearchResult;
import com.liyv.search.service.SolrItemService;
import com.liyv.taotao.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @Autowired
    SolrItemService solrItemService;

    @GetMapping("/q")
    public Result search(@RequestParam(defaultValue = "") String keyword,
                         @RequestParam(defaultValue = "1") int page,
                         @RequestParam(defaultValue = "50") int rows) {
        try {
            SearchResult result = solrItemService.search(keyword, page, rows);
            return new Result<>(true, result);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "查询失败!");
        }
    }
}
