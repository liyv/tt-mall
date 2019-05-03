package com.liyv.rest.service.impl;

import com.liyv.rest.service.ItemService;
import com.liyv.rest.service.RedisService;
import com.liyv.taotao.entity.TaoItem;
import com.liyv.taotao.mapper.TaoItemMapper;
import com.liyv.taotao.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TaoItemMapper itemMapper;

    @Autowired
    private RedisService redisService;

    @Value("${REDIS_ITEM_KEY}")
    String REDIS_ITEM_KEY;
    @Value("${ITEM_BASE_INFO_KEY}")
    String ITEM_BASE_INFO_KEY;
    @Value("${ITEM_EXPIRE_SECOND}")
    int ITEM_EXPIRE_SECOND;

    @Override
    public TaoItem getItemByID(long itemId) {
        String key = REDIS_ITEM_KEY + ":" + ITEM_BASE_INFO_KEY + ":" + itemId;
        //查询缓存,
        try {
            String json = redisService.get(key);
            if (!StringUtils.isEmpty(json)) {
                //把json数据转换为Java对象
                return JsonUtils.json2Obj(json, TaoItem.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //根据id从数据库获取
        TaoItem item = itemMapper.selectByPrimaryKey(itemId);
        //向 Redis中添加缓存,设置过期时间
        try {

            redisService.set(key, JsonUtils.objectToJson(item));
            //设置key 的过期时间
            redisService.expire(key, ITEM_EXPIRE_SECOND);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }
}
