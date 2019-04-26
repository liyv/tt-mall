package com.liyv.taotao.service;

import com.liyv.taotao.dto.TaoItemCatDTO;
import com.liyv.taotao.entity.ContentCategoryEntity;

import java.util.List;

/**
 * 内容分类
 */
public interface TaoContentCategoryService {

    public List<TaoItemCatDTO> listContentCategoryByPid(long pid);

    public TaoItemCatDTO saveContentCategory(ContentCategoryEntity entity);

    public int updateCategory(long id, String name);

    public int deleteCategory(long id);
}
