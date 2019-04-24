package com.liyv.taotao.web;

import com.liyv.taotao.dto.TaoItemCatDTO;
import com.liyv.taotao.service.TaoItemCatService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TaoItemCatServiceTest extends BaseTest {

    @Autowired
    private TaoItemCatService taoItemCatService;

    @Test
    public void testGoodsCategory() {
        List<TaoItemCatDTO> list = taoItemCatService.listCategoryByPid(0L);
        System.out.println("====================");
        System.out.println(list.size());
        if (list.size() > 0) {
            System.out.println(list.get(0));
        }
        System.out.println("====================");
    }
}
