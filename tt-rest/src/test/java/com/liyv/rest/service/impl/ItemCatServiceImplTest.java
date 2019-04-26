package com.liyv.rest.service.impl;

import com.liyv.rest.BaseTest;
import com.liyv.taotao.dto.CatNode;
import com.liyv.rest.pojo.CatResult;
import com.liyv.rest.service.ItemCatService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemCatServiceImplTest extends BaseTest {
    @Autowired
    ItemCatService itemCatService;

    @Test
    public void getItemCatList() {
        CatResult result = itemCatService.getItemCatList();
        CatNode catNode = (CatNode) result.getData().get(0);
        System.out.println(catNode);
        System.out.println(result.getData().size());
    }
}