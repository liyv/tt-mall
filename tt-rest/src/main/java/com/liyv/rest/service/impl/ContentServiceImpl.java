package com.liyv.rest.service.impl;

import com.liyv.rest.service.ContentService;
import com.liyv.rest.service.RedisService;
import com.liyv.taotao.dto.content.ContentItemDTO;
import com.liyv.taotao.mapper.TaoContentMapper;
import com.liyv.taotao.utils.JsonUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TaoContentMapper taoContentMapper;

    @Autowired
    private RedisService redisService;

    @Value("${INDEX_CATEGORY_REDIS_HASH}")
    private String INDEX_CATEGORY_REDIS_HASH;

    @Override
    public List<ContentItemDTO> getContentListByCategory(long categoryId) {

        //从 Redis 取缓存
        try {
            byte[] bytes = redisService.hget(INDEX_CATEGORY_REDIS_HASH.getBytes(), ("banner" + categoryId).getBytes());
            //缓存命中
            if (ArrayUtils.isNotEmpty(bytes)) {
                List<ContentItemDTO> list = JsonUtils.byte2List(bytes, ContentItemDTO.class);
                return list;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        List<ContentItemDTO> result = taoContentMapper.listContentByCategoryId(categoryId);
        //存缓存
        try {
            long id = redisService.hset(INDEX_CATEGORY_REDIS_HASH.getBytes(), ("banner" + categoryId).getBytes(), JsonUtils.obj2Byte(result));
            System.out.println(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public long deleteRedisCache(long categoryId) {
        try {
            long id= redisService.hDel(INDEX_CATEGORY_REDIS_HASH.getBytes(), ("banner" + categoryId).getBytes());
            return id;
        } catch (Exception e) {
            return -1;
        }
    }
}
