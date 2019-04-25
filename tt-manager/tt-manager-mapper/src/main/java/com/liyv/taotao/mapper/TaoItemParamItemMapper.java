package com.liyv.taotao.mapper;

import com.liyv.taotao.entity.TaoItemParamItemEntity;

/**
 * 单个商品的规格参数数据
 */
public interface TaoItemParamItemMapper {

    //保存单个商品规格参数
    public int saveParamItem(TaoItemParamItemEntity entity);
}
