package com.liyv.taotao.mapper;

import com.liyv.taotao.entity.ContentEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaoContentMapper {

    public List<ContentEntity> listContent(@Param("categoryId") long categoryId, @Param("start") int start, @Param("size") int size);

    public int countContent(long categoryId);

    public int insertContent(ContentEntity entity);

    public int updateContent(ContentEntity entity);

    public int deleteContent(List<Long> id);
}
