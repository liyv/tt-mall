package com.liyv.taotao.service;

import com.liyv.taotao.dto.EUDataGridDTO;
import com.liyv.taotao.entity.TaoItemParamEntity;

public interface ItemParamService {

    /**
     * 根据类别id ,查找模板数据
     *
     * @param catId
     * @return
     */
    public TaoItemParamEntity selectByCatId(long catId);

    /**
     * 保存商品类目模板数据
     * @param entity
     * @return
     */
    public int saveItemParam(TaoItemParamEntity entity);

    /**
     * 获取规格参数列表数据
     * @param page
     * @param rows
     * @return
     */
    public EUDataGridDTO listItemParm(int page,int rows);
}
