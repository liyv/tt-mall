package com.liyv.taotao.web;


import com.liyv.taotao.entity.dto.ItemListDTO;
import com.liyv.taotao.service.ItemService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemControllerTest extends BaseTest {

    @Autowired
    ItemService itemService;

    /**
     * 测试查询商品列表
     */
    @Test
    public void testListGoods() {
        ItemListDTO dto = itemService.listGoods(0, 30);
        System.out.println(dto.getTotal());
    }
}