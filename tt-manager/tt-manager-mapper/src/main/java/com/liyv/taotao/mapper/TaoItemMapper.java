package com.liyv.taotao.mapper;

import com.liyv.taotao.entity.TaoItem;

public interface TaoItemMapper {

    TaoItem selectByPrimaryKey(long itemId);
}
