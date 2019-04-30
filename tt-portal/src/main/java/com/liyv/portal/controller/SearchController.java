package com.liyv.portal.controller;

import com.liyv.portal.dto.SearchResult;
import com.liyv.portal.service.SearchService;
import com.liyv.taotao.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    @Autowired
    SearchService searchService;

    @RequestMapping("/search")
    public String search(@RequestParam("q") String keyword,
                         @RequestParam(defaultValue = "1") int page,
                         @RequestParam(defaultValue = "60") int rows,
                         Model model) {
        try {
            SearchResult searchResult = searchService.search(keyword, page, rows);
            //传递参数给页面
            model.addAttribute("query", keyword);
            model.addAttribute("totalPages", searchResult.getPageCount());
            model.addAttribute("itemList", searchResult.getItemList());
            model.addAttribute("page", searchResult.getCurPage());
            return "search";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error/exception";
    }
}
