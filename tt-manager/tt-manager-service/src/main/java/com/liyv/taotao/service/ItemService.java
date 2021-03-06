package com.liyv.taotao.service;

import com.liyv.taotao.entity.TaoItem;
import com.liyv.taotao.dto.ItemListDTO;

public interface ItemService {

    TaoItem getItemById(long itemId);

    ItemListDTO listGoods(int page,int rows);

    int insertItem(TaoItem item,String itemParams);
}
