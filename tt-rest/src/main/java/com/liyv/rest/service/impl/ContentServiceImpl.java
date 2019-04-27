package com.liyv.rest.service.impl;

import com.liyv.rest.service.ContentService;
import com.liyv.taotao.dto.content.ContentItemDTO;
import com.liyv.taotao.mapper.TaoContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    TaoContentMapper taoContentMapper;

    @Override
    public List<ContentItemDTO> getContentListByCategory(long categoryId) {
        return taoContentMapper.listContentByCategoryId(categoryId);
    }
}
