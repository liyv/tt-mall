package com.liyv.taotao.mapper;

import com.liyv.taotao.entity.TaoMenuEntity;

import java.util.List;

public interface TaoMenuMapper {
    /**
     * 获取所有的菜单
     *
     * @return
     */
    List<TaoMenuEntity> listMenu();

    /**
     * 选取
     * @param pid
     * @return
     */
    List<TaoMenuEntity> listSubMenu(int pid);
}
