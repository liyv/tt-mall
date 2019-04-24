package com.liyv.taotao.web;


import com.liyv.taotao.dto.ItemListDTO;
import com.liyv.taotao.entity.TaoItem;
import com.liyv.taotao.service.ItemService;
import com.liyv.taotao.service.TaoItemCatService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemControllerTest extends BaseTest {

    @Autowired
    ItemService itemService;

    @Autowired
    TaoItemCatService catService;

    /**
     * 测试查询商品列表
     */
    @Test
    public void testListGoods() {
        ItemListDTO dto = itemService.listGoods(0, 30);
        System.out.println(dto.getTotal());
    }

    /**
     * 测试新增商品
     */
    @Test
    public void addItem() {
        TaoItem item = new TaoItem();
        item.setId(System.currentTimeMillis());
        item.setTitle("测试");
        item.setSellPoint("满100-50");
        item.setPrice(5000);
        item.setNum(1000);
        item.setBarcode("123456");
        item.setImage("/resource/uploadImg/2019-04/1556030643262958.png");
        item.setCid(57);
        short status = 1;
        item.setStatus(status);
        int row = itemService.insertItem(item);
        System.out.println("====================");
        System.out.println(row);
    }
}