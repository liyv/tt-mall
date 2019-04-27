package com.liyv.taotao.service.impl;

import com.liyv.taotao.dto.EUDataGridDTO;
import com.liyv.taotao.entity.ContentEntity;
import com.liyv.taotao.mapper.TaoContentMapper;
import com.liyv.taotao.service.TaoContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaoContentServiceImpl implements TaoContentService {


    @Autowired
    private TaoContentMapper contentMapper;

    @Override
    public EUDataGridDTO listContent(long categoryId, int page, int rows) {
        List<ContentEntity> list = contentMapper.listContent(categoryId, rows * (page - 1), rows);
        EUDataGridDTO dto = new EUDataGridDTO();
        dto.setRows(list);
        dto.setTotal(contentMapper.countContent(categoryId));
        return dto;
    }

    @Override
    public int saveContent(ContentEntity entity) {
        return contentMapper.insertContent(entity);
    }

    @Override
    public int updateContent(ContentEntity entity) {
        return contentMapper.updateContent(entity);
    }

    @Override
    public int deleteContent(List<Long> ids) {
        return contentMapper.deleteContent(ids);
    }
}
