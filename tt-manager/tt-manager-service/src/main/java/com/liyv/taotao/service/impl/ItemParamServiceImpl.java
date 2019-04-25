package com.liyv.taotao.service.impl;

import com.liyv.taotao.dto.EUDataGridDTO;
import com.liyv.taotao.dto.TaoItemParamDTO;
import com.liyv.taotao.entity.TaoItemParamEntity;
import com.liyv.taotao.mapper.TaoItemParamMapper;
import com.liyv.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    TaoItemParamMapper paramMapper;


    @Override
    public TaoItemParamEntity selectByCatId(long catId) {
        return paramMapper.selectByCatId(catId);
    }

    @Override
    public int saveItemParam(TaoItemParamEntity entity) {
        return paramMapper.saveItemParam(entity);
    }

    @Override
    public EUDataGridDTO listItemParm(int page, int rows) {
        List<TaoItemParamDTO> list = paramMapper.listItemCategoryParam(rows * (page - 1), rows);
        EUDataGridDTO dto = new EUDataGridDTO();
        dto.setRows(list);
        int count = paramMapper.countItemCategoryParam();
        dto.setTotal(count);
        return dto;
    }
}
