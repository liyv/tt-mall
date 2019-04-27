package com.liyv.portal.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liyv.portal.pojo.AdNode;
import com.liyv.portal.service.ContentService;
import com.liyv.taotao.dto.Result;
import com.liyv.taotao.dto.content.ContentItemDTO;
import com.liyv.taotao.utils.JsonUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {


    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${REST_CONTENT_URL}")
    private String REST_CONTENT_URL;
    @Value("${REST_CONTENT_AD_CID}")
    private String REST_CONTENT_AD_CID;


    @Override
    public String getAdList() {
        //调用服务层获取数据
        String url = REST_BASE_URL + REST_CONTENT_URL + REST_CONTENT_AD_CID;
        System.out.println(url);
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).get().build();
        try (Response response = httpClient.newCall(request).execute()) {
            //将结果转为对象
            Result result = Result.formatToList(response.body().string(), ContentItemDTO.class);
            //取出datas,将datas转为字符串
            List<ContentItemDTO> contentList = (List<ContentItemDTO>) result.getData();
            List<AdNode> adNodeList = new ArrayList<>(contentList.size());
            for (ContentItemDTO dto : contentList) {
                AdNode node = new AdNode();
                node.setHeight(240);
                node.setWidth(670);
                node.setSrc(dto.getPic());
                node.setHeightB(240);
                node.setWidthB(550);
                node.setSrcB(dto.getPic2());
                node.setAlt(dto.getTitle());
                node.setHref(dto.getUrl());
                adNodeList.add(node);
            }
            String resultStr = JsonUtils.objectToJson(adNodeList);
            return resultStr;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
