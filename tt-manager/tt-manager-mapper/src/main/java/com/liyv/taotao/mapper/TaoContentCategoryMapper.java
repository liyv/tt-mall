package com.liyv.taotao.mapper;

import com.liyv.taotao.dto.TaoItemCatDTO;
import com.liyv.taotao.entity.ContentCategoryEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 内容分类
 */
public interface TaoContentCategoryMapper {


    public ContentCategoryEntity selectCategoryById(long id);

    /**
     * 根据Pid获取内容的分类信息
     *
     * @param pid
     * @return
     */
    public List<TaoItemCatDTO> listContentCategoryByPid(long pid);

    /**
     * 插入新的内容分类数据,并返回该记录
     */
    public long saveContentCategory(ContentCategoryEntity entity);

    /**
     * 分类项升级为 parent
     */
    public int upgradeCategory(long id);

    /**
     * 分类降级
     */
    public int degradeCategory(long id);

    /**
     * 更新分类的名称
     */
    public int updateCategory(@Param("id") long id, @Param("name") String name);

    /**
     * 根据id删除分类
     */
    public int deleteCategoryById(long id);

    /**
     * 根据pid删除分类
     */
    public int deleteCategoryByParent(long pid);

    /**
     * 查询分类下的子分类个数,判断是否要降级
     */
    public int countSubCategory(long parentId);
}
