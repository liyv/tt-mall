package com.liyv.portal.service.impl;

import com.liyv.portal.pojo.PortalItem;
import com.liyv.portal.pojo.TaoItem;
import com.liyv.portal.service.ItemService;
import com.liyv.taotao.dto.Result;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${REST_ITEM_BASE_URL}")
    private String REST_ITEM_BASE_URL;


    @Override
    public TaoItem getItemById(long itemId) {
        //okhttp 调用服务

        String url = REST_BASE_URL + REST_ITEM_BASE_URL + itemId;
//        System.out.println(url);
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).get().build();
        try (Response response = httpClient.newCall(request).execute()) {
            String json = response.body().string();
            Result result = Result.formatToPojo(json, PortalItem.class);
            TaoItem item = (TaoItem) result.getData();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
