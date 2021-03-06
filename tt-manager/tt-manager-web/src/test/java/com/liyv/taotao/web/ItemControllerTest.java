package com.liyv.taotao.web;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liyv.taotao.dto.EUDataGridDTO;
import com.liyv.taotao.dto.ItemListDTO;
import com.liyv.taotao.dto.TaoItemCatDTO;
import com.liyv.taotao.entity.ContentCategoryEntity;
import com.liyv.taotao.entity.ContentEntity;
import com.liyv.taotao.entity.TaoItem;
import com.liyv.taotao.entity.TaoItemParamEntity;
import com.liyv.taotao.mapper.TaoItemParamMapper;
import com.liyv.taotao.service.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.json.Jackson2JsonDecoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItemControllerTest extends BaseTest {

    @Autowired
    ItemService itemService;

    @Autowired
    TaoItemCatService catService;
    @Autowired
    ItemParamService paramService;
    @Autowired
    TaoContentCategoryService contentCategoryService;
    @Autowired
    TaoContentService contentService;

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
        item.setTitle("测试2");
        item.setSellPoint("满100-50");
        item.setPrice(5000);
        item.setNum(1000);
        item.setBarcode("123456");
        item.setImage("/resource/uploadImg/2019-04/1556030643262958.png");
        item.setCid(57);
        short status = 1;
        item.setStatus(status);
        int row = itemService.insertItem(item, "123456");
        System.out.println("====================");
        System.out.println(row);
    }

    /**
     * 测试根据类别id查找模板数据
     */
    @Test
    public void selectParamTest() {
        TaoItemParamEntity entity = paramService.selectByCatId(560);
        System.out.println(entity);
    }

    /**
     * 规格参数列表数据
     */
    @Test
    public void listItemParam() {
        EUDataGridDTO dto = paramService.listItemParm(1, 1);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String str = objectMapper.writeValueAsString(dto);
            System.out.println(str);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试内容分类信息
     */
    @Test
    public void listContentCategory() {
        List<TaoItemCatDTO> catList = contentCategoryService.listContentCategoryByPid(0);
        System.out.println(catList.get(0));
    }

    /**
     * 测试新增内容分类
     */
    @Test
    public void insertContentCategory() {
        ContentCategoryEntity entity = new ContentCategoryEntity();
        entity.setName("新建见识");
        byte isParent = 0;
        entity.setIsParent(isParent);
        entity.setParentId(86);
        entity.setSortOrder(4);
        byte status = 1;
        entity.setStatus(status);
        entity.setCreated(new Date());
        entity.setUpdated(new Date());
        TaoItemCatDTO dto = contentCategoryService.saveContentCategory(entity);
        System.out.println(dto.getId());
    }

    /**
     * 修改分类
     */
    @Test
    public void updateCategory() {
        int row = contentCategoryService.updateCategory(101, "大光高");
        System.out.println(row);
    }

    /**
     * 删除分类
     */
    @Test
    public void deleteCategory() {
        int id = 98;
        int row = contentCategoryService.deleteCategory(id);
        System.out.println(row);
    }

    /**
     * 测试内容列表
     */
    @Test
    public void listContent() {
        EUDataGridDTO dto = contentService.listContent(89, 1, 10);
        System.out.println(dto.getTotal());
    }

    /**
     * 插入新的内容
     */
    @Test
    public void saveContent() {
        ContentEntity entity = new ContentEntity();
        entity.setCategoryId(90);
        entity.setTitle("贵州");
        entity.setSubTitle("贵州日报");
        entity.setTitleDesc("工作");
        entity.setUrl("http://www.sina.com.cn");
        entity.setPic("http://www.sina.com.cn");
        entity.setPic2("http://www.sina.com.cn");
        entity.setContent("去");
        entity.setCreated(new Date());
        entity.setUpdated(new Date());
        int row = contentService.saveContent(entity);
        System.out.println(row);
    }

    /**
     * 更新内容
     */
    @Test
    public void updateContent() {
        ContentEntity entity = new ContentEntity();
        entity.setId(32);
        entity.setContent("去2");
        int row = contentService.updateContent(entity);
        System.out.println(row);
    }

    /**
     * 删除内容
     */
    @Test
    public void deleteContent() {
        List<Long> ids = new ArrayList<>();
        ids.add(33L);
        ids.add(29L);
        int row = contentService.deleteContent(ids, 89);
        System.out.println(row);
    }
}