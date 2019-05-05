package com.liyv.sso.mapper;

import com.liyv.taotao.entity.TaoUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SSoUserMapper {

    /**
     * 根据特定字段,查询是否存在该记录
     * @param filed
     * @param value
     * @return
     */
    public List<TaoUser> selectByField(@Param("field") String filed, @Param("val") String value);

    /**
     * 增加新的用户
     * @param user
     * @return
     */
    public int insertUser(TaoUser user);

    /**
     * 根据用户名提取数据,
     * @param username
     * @return
     */
    public List<TaoUser> selectByUsername(String username);
}
