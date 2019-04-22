package com.liyv.taotao.service.impl;

import com.liyv.taotao.entity.TaoItem;
import com.liyv.taotao.mapper.TaoItemMapper;
import com.liyv.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TaoItemMapper taoItemMapper;

    @Override
    public TaoItem getItemById(long itemId) {
        TaoItem item = taoItemMapper.selectByPrimaryKey(itemId);
        return item;
    }
}
