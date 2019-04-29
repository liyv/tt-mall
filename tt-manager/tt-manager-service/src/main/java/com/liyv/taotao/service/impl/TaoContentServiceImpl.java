package com.liyv.taotao.service.impl;

import com.liyv.taotao.dto.EUDataGridDTO;
import com.liyv.taotao.dto.Result;
import com.liyv.taotao.entity.ContentEntity;
import com.liyv.taotao.mapper.TaoContentMapper;
import com.liyv.taotao.service.TaoContentService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaoContentServiceImpl implements TaoContentService {


    @Autowired
    private TaoContentMapper contentMapper;
    @Value("${CACHE_REDIS_BASE_URL}")
    private String CACHE_REDIS_BASE_URL;
    @Value("${CACHE_REDIS_SYNC_PATH}")
    private String CACHE_REDIS_SYNC_PATH;

    @Override
    public EUDataGridDTO listContent(long categoryId, int page, int rows) {
        List<ContentEntity> list = contentMapper.listContent(categoryId, rows * (page - 1), rows);
        EUDataGridDTO dto = new EUDataGridDTO();
        dto.setRows(list);
        dto.setTotal(contentMapper.countContent(categoryId));
        return dto;
    }

    @Override
    public int saveContent(ContentEntity entity) {
        try {
            syncCache(entity.getCategoryId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentMapper.insertContent(entity);
    }

    @Override
    public int updateContent(ContentEntity entity) {
        try {
            syncCache(entity.getCategoryId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentMapper.updateContent(entity);
    }

    @Override
    public int deleteContent(List<Long> ids,long categoryId) {
        int rows= contentMapper.deleteContent(ids);
        try {
            syncCache(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

    private void syncCache(long categoryId) throws Exception {
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder().url(CACHE_REDIS_BASE_URL + CACHE_REDIS_SYNC_PATH + categoryId).get().build();
        try (Response response = httpClient.newCall(request).execute()) {
            Result result = Result.format(response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
