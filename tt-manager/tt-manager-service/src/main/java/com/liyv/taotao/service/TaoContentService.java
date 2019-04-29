package com.liyv.taotao.service;

import com.liyv.taotao.dto.EUDataGridDTO;
import com.liyv.taotao.entity.ContentEntity;

import java.util.List;

public interface TaoContentService {

    public EUDataGridDTO listContent(long categoryId, int page, int rows);

    public int saveContent(ContentEntity entity);

    public int updateContent(ContentEntity entity);

    public int deleteContent(List<Long> id,long categoryId);
}
