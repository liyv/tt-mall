package com.liyv.rest.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liyv.rest.service.RedisService;
import com.liyv.taotao.dto.CatNode;
import com.liyv.rest.pojo.CatResult;
import com.liyv.rest.service.ItemCatService;
import com.liyv.taotao.mapper.TaoItemCatMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TaoItemCatMapper itemCatMapper;
    @Autowired
    private RedisService redisService;

    @Value("${INDEX_CATEGORY_REDIS_HASH}")
    private String INDEX_CATEGORY_REDIS_HASH;

    @Override
    public String getItemCatList() {

        //从 Redis中读取缓存
        try {
            String val = redisService.hget(INDEX_CATEGORY_REDIS_HASH, "menu");
            if (StringUtils.isNotEmpty(val)) {
                return val;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        List<?> nodeList = itemCatMapper.listCatList();//catnode 列表
        //遍历list.重新整理数据格式

//         "data": [
//         {
        //   1级数据
//            "u": "/products/1.html",
//            "n": "<a href='/products/1.html'>图书、音像、电子书刊</a>",
//            "i": [
//                {
        //       2级数据
//                    "u": "/products/2.html",
//                    "n": "电子书刊",
//                    "i": [
//                              "/products/3.html|电子书",
//                              "/products/4.html|网络原创",
//                              "/products/5.html|数字杂志",
//                               "/products/6.html|多媒体图书"
//                          ]
//                  }
//                    ]
//          }]
        ArrayList<String> leafList = new ArrayList<>();
        modifyList(nodeList, true, leafList);
        CatResult result = new CatResult();
        result.setData(nodeList);
        //把pojo转换成字符串
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try {
            json = objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        try {
            redisService.hset(INDEX_CATEGORY_REDIS_HASH, "menu", json);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return json;
    }

    //调整 list节点数据组织结构

    /**
     * @param nodeList 父级节点数据
     * @param isTop    是否是顶级菜单
     * @param leafList 用于存放叶级数据,若没有子级数据,则将自己的url放到其中
     */
    private void modifyList(List nodeList, boolean isTop, List<String> leafList) {
        CatNode catNode;
        StringBuilder sb;
        ArrayList<String> leafList2;
        for (Object obj : nodeList) {
            catNode = (CatNode) obj;
            if (catNode.getItem() != null && catNode.getItem().size() > 0) {
                //创建一个用于存放叶级数据的list,
                leafList2 = new ArrayList<>();
                modifyList(catNode.getItem(), false, leafList2);
                if (leafList2.size() > 0) {
                    //说明是非叶数据
                    catNode.setItem(leafList2);
                }
            } else {
                //说明是叶级数据,将其自身放到list中
                leafList.add(catNode.getUrl());
            }
            sb = new StringBuilder();
            if (isTop) {
                sb.append("<a href='").append(catNode.getUrl()).append("'>").append(catNode.getName()).append("</a>");
                catNode.setName(sb.toString());
            }
        }
    }
}
