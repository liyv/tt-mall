package com.liyv.rest.service;

import com.liyv.taotao.dto.content.ContentItemDTO;

import java.util.List;

public interface ContentService {

    public List<ContentItemDTO> getContentListByCategory(long categoryId);
}
