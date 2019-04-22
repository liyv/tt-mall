package com.liyv.taotao.mapper;

import com.liyv.taotao.entity.TaoItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaoItemMapper {

    TaoItem selectByPrimaryKey(long itemId);

    List<TaoItem> listItem(@Param("start") int start,@Param("size") int row);

    int countItem();
}
