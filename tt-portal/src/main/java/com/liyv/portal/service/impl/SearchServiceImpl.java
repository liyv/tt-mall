package com.liyv.portal.service.impl;

import com.liyv.portal.dto.SearchResult;
import com.liyv.portal.service.SearchService;
import com.liyv.taotao.dto.Result;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {

    @Value("${SEARCH_BASE_URL}")
    String SEARCH_BASE_URL;

    @Override
    public SearchResult search(String keyword, int page, int rows) throws IOException{
        //调用服务器查询商品列表
        Map<String, Object> param = new HashMap<>();
        param.put("keyword", keyword);
        param.put("page", page);
        param.put("rows", rows);

        //调用服务
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse(SEARCH_BASE_URL).newBuilder();
        builder.addQueryParameter("keyword", keyword);
        builder.addQueryParameter("page", page + "");
        builder.addQueryParameter("rows", rows + "");
        Request request = new Request.Builder().url(builder.build()).get().build();
        try (Response response = client.newCall(request).execute()) {
            Result result = Result.formatToPojo(response.body().string(), SearchResult.class);
            //取回数据
            SearchResult searchResult = (SearchResult) result.getData();
            return searchResult;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

    }
}
