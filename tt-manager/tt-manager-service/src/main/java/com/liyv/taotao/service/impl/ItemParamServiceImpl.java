package com.liyv.taotao.service.impl;

import com.liyv.taotao.entity.TaoItemParamEntity;
import com.liyv.taotao.mapper.TaoItemParamMapper;
import com.liyv.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
