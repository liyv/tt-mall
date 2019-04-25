package com.liyv.taotao.mapper;

import com.liyv.taotao.dto.TaoItemParamDTO;
import com.liyv.taotao.entity.TaoItemParamEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaoItemParamMapper {


    public TaoItemParamEntity selectByCatId(long catId);

    public int saveItemParam(TaoItemParamEntity entity);

    public List<TaoItemParamDTO> listItemCategoryParam(@Param("start") int start,@Param("size") int size);

    public int countItemCategoryParam();
}
