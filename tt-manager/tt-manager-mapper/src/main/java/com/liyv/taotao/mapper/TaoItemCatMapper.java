package com.liyv.taotao.mapper;

import com.liyv.taotao.dto.TaoItemCatDTO;

import java.util.List;

/**
 * 商品种类
 */
public interface TaoItemCatMapper {

    /**
     * 列出所有的商品种类
     *
     * @return
     */
    public List<TaoItemCatDTO> listGoodsCategoryByPid(long pid);

}
