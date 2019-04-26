package com.liyv.taotao.mapper;

import com.liyv.taotao.dto.TaoItemCatDTO;
import com.liyv.taotao.entity.TaoItemCatEntity;

import java.util.List;

/**
 * 商品种类
 */
public interface TaoItemCatMapper {

    /**
     * 根据Pid列出所有的商品种类
     *
     * @return
     */
    public List<TaoItemCatDTO> listGoodsCategoryByPid(long pid);

    /**
     * 列出所有的商品种类
     * @return
     */
    public List<?> listCatList();

}
