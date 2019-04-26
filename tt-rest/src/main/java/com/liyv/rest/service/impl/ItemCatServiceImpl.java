package com.liyv.rest.service.impl;

import com.liyv.rest.pojo.CatNode;
import com.liyv.rest.pojo.CatResult;
import com.liyv.rest.service.ItemCatService;
import com.liyv.taotao.mapper.TaoItemCatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TaoItemCatMapper itemCatMapper;

    @Override
    public CatResult getItemCatList() {
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
        return result;
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
