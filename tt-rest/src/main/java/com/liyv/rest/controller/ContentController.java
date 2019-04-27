package com.liyv.rest.controller;

import com.liyv.rest.service.ContentService;
import com.liyv.taotao.dto.Result;
import com.liyv.taotao.dto.content.ContentItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("content")
public class ContentController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ContentService contentService;

    @GetMapping("/list/{categoryId}")
    public Result listContent(@PathVariable("categoryId") long categoryId) {
        try {
            List<ContentItemDTO> list = contentService.getContentListByCategory(categoryId);
            return new Result<>(true, list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            //e.getMessage() 可能获取不到有效错误信息
            return new Result<>(false, e.getMessage());
        }
    }
}
