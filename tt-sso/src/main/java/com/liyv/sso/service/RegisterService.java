package com.liyv.sso.service;

import com.liyv.taotao.dto.Result;
import com.liyv.taotao.entity.TaoUser;

public interface RegisterService {
    /**
     * 数据校验
     * @param param
     * @param type
     * @return
     */
    public int checkData(String param, int type);

    public Result register(TaoUser user);
}
