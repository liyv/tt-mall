package com.liyv.sso.service.impl;

import com.liyv.sso.mapper.SSoUserMapper;
import com.liyv.sso.service.RegisterService;
import com.liyv.taotao.dto.Result;
import com.liyv.taotao.entity.TaoUser;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private SSoUserMapper sSoUserMapper;

    @Override
    public int checkData(String param, int type) {
        //根据特定类型检测数据
        //1,2,3分别代表username,phone,email
        String field = "";
        if (1 == type) {
            field = "username";
        } else if (2 == type) {
            field = "phone";
        } else if (3 == type) {
            field = "email";
        }
        List<TaoUser> users = sSoUserMapper.selectByField(field, param);
        int result;
        if (!CollectionUtils.isEmpty(users)) {
            result = users.size();
        } else {
            result = -1;
        }
        return result;
    }

    @Override
    public Result register(TaoUser user) {
        //校验数据
        if (StringUtils.isEmpty(user.getUsername()) ||
                StringUtils.isEmpty(user.getPassword())) {
            return new Result(false, "用户名或密码不能为空");
        }
        //校验数据是否重复
        int userRow = checkData(user.getUsername(), 1);
        if (userRow > 0) {
            return new Result(false, "用户名已存在");
        }
        //校验手机号
        if (!StringUtils.isEmpty(user.getPhone())) {
            int phoneRow = checkData(user.getPhone(), 2);
            //
            if (phoneRow > 0) {
                //手机号已存在
                return new Result(false, "手机号重复");
            }
        }
        //邮箱校验
        if (!StringUtils.isEmpty(user.getEmail())) {
            int phoneRow = checkData(user.getEmail(), 3);
            //
            if (phoneRow > 0) {
                //手机号已存在
                return new Result(false, "邮箱重复");
            }
        }
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        user.setCreated(new Date());
        user.setUpdated(new Date());
        int rowUpdate = sSoUserMapper.insertUser(user);
        if (rowUpdate > 0) {
            return new Result(true, rowUpdate);
        }
        return new Result(false, "用户注册失败!");
    }
}
