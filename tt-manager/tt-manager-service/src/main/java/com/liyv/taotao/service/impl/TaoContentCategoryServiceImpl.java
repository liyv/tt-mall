package com.liyv.taotao.service.impl;

import com.liyv.taotao.dto.TaoItemCatDTO;
import com.liyv.taotao.entity.ContentCategoryEntity;
import com.liyv.taotao.mapper.TaoContentCategoryMapper;
import com.liyv.taotao.service.TaoContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaoContentCategoryServiceImpl implements TaoContentCategoryService {

    @Autowired
    private TaoContentCategoryMapper contentCategoryMapper;

    @Override
    public List<TaoItemCatDTO> listContentCategoryByPid(long pid) {
        return contentCategoryMapper.listContentCategoryByPid(pid);
    }

    @Override
    @Transactional
    public TaoItemCatDTO saveContentCategory(ContentCategoryEntity entity) {
        //保存新分类
        contentCategoryMapper.saveContentCategory(entity);

        //更新父类信息,升级为parent的情况
        int updateRow = contentCategoryMapper.upgradeCategory(entity.getParentId());
        TaoItemCatDTO dto = new TaoItemCatDTO();
        dto.setId(entity.getId());
        dto.setText(entity.getName());
        //
        return dto;
    }

    @Override
    public int updateCategory(long id, String name) {
        return contentCategoryMapper.updateCategory(id, name);
    }

    @Override
    @Transactional
    public int deleteCategory(long id) {
        //根据Id删除分类
        ContentCategoryEntity entity = contentCategoryMapper.selectCategoryById(id);
        int row1 = contentCategoryMapper.deleteCategoryById(id);
        //查询parent 下的子分类,若没有子分类了,则降级
        int count = contentCategoryMapper.countSubCategory(entity.getParentId());
        if (count == 0) {
            //降级
            int row3 = contentCategoryMapper.degradeCategory(entity.getParentId());
        }
        //若该id是parent类型,则删除其所有子分类
        int row2 = 0;
        if (entity.getIsParent() == 1) {
            row2 = contentCategoryMapper.deleteCategoryByParent(id);
        }
        return row1 + row2;
    }
}
