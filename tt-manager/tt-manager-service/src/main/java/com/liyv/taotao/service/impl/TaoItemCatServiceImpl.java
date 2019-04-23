package com.liyv.taotao.service.impl;

import com.liyv.taotao.entity.TaoItemCatEntity;
import com.liyv.taotao.mapper.TaoItemCatMapper;
import com.liyv.taotao.service.TaoItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaoItemCatServiceImpl implements TaoItemCatService {


    @Autowired
    private TaoItemCatMapper taoItemCatMapper;

    @Override
    public List<TaoItemCatEntity> listCategory() {
        return taoItemCatMapper.listGoodsCategory();
    }
}
