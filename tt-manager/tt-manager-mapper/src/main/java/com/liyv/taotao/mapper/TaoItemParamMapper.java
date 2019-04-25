package com.liyv.taotao.mapper;

import com.liyv.taotao.entity.TaoItemParamEntity;

public interface TaoItemParamMapper {


    public TaoItemParamEntity selectByCatId(long catId);

    public int saveItemParam(TaoItemParamEntity entity);
}
