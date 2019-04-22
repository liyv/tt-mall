package com.liyv.taotao.service.impl;

import com.liyv.taotao.entity.TaoMenuEntity;
import com.liyv.taotao.mapper.TaoMenuMapper;
import com.liyv.taotao.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private TaoMenuMapper menuMapper;

    @Override
    public List<TaoMenuEntity> listMenu() {
        return menuMapper.listMenu();
    }
}
