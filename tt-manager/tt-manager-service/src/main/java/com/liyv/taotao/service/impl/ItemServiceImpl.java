package com.liyv.taotao.service.impl;

import com.liyv.taotao.entity.TaoItem;
import com.liyv.taotao.dto.ItemListDTO;
import com.liyv.taotao.entity.TaoItemParamItemEntity;
import com.liyv.taotao.mapper.TaoItemMapper;
import com.liyv.taotao.mapper.TaoItemParamItemMapper;
import com.liyv.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TaoItemMapper taoItemMapper;
    @Autowired
    private TaoItemParamItemMapper itemParamItemMapper;

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
    @Transactional
    public int insertItem(TaoItem item, String itemParams) {
        //插入商品信息
        int itemRow = taoItemMapper.insertItem(item);
        //插入规格参数数据
        TaoItemParamItemEntity entity = new TaoItemParamItemEntity();
        entity.setItemId(item.getId());
        entity.setParamData(itemParams);
        int itemPramItemRow = itemParamItemMapper.saveParamItem(entity);
        return (itemRow + itemPramItemRow);
    }
}
