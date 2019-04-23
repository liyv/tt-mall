package com.liyv.taotao.web;

import com.liyv.taotao.entity.TaoItemCatEntity;
import com.liyv.taotao.service.TaoItemCatService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TaoItemCatServiceTest extends BaseTest{

    @Autowired
    private TaoItemCatService taoItemCatService;

    @Test
    public void testGoodsCategory(){
        List<TaoItemCatEntity> list=taoItemCatService.listCategory();
        System.out.println("====================");
        System.out.println(list.size());
        System.out.println("====================");
    }
}
