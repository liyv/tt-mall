package com.liyv.taotao.service.impl;

import com.liyv.taotao.entity.TaoItem;
import com.liyv.taotao.dto.ItemListDTO;
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

    @Override
    public ItemListDTO listGoods(int page, int rows) {
        ItemListDTO dto = new ItemListDTO();
        dto.setRows(taoItemMapper.listItem(rows * (page - 1), rows));
        dto.setTotal(taoItemMapper.countItem());
        return dto;
    }

    @Override
    public int insertItem(TaoItem item) {
        return taoItemMapper.insertItem(item);
    }
}
