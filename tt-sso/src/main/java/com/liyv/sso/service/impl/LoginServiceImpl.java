package com.liyv.sso.service.impl;

import com.liyv.sso.mapper.SSoUserMapper;
import com.liyv.sso.service.LoginService;
import com.liyv.sso.service.RedisService;
import com.liyv.taotao.dto.Result;
import com.liyv.taotao.entity.TaoUser;
import com.liyv.taotao.utils.CookieUtils;
import com.liyv.taotao.utils.JsonUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {


    @Autowired
    private SSoUserMapper sSoUserMapper;
    @Autowired
    private RedisService redisService;

    @Value("${REDIS_SESSION_KEY}")
    String REDIS_SESSION_KEY;
    @Value("${REDIS_SESSION_EXPIRE}")
    int REDIS_SESSION_EXPIRE;

    @Override
    public Result login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        //校验用户名密码是否正确
        List<TaoUser> userList = sSoUserMapper.selectByUsername(username);
        if (CollectionUtils.isEmpty(userList)) {
            return new Result(false, "用户名或密码错误");
        }

        TaoUser user = userList.get(0);
        //校验密码
        if (!user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
            return new Result(false, "用户名或密码错误");
        }

        //登录成功
        //生成token
        String token = UUID.randomUUID().toString();
        //把用户id写入reids
        user.setPassword(null);
        String redis_key = REDIS_SESSION_KEY + ":" + token;
        redisService.set(redis_key, JsonUtils.objectToJson(user));
        redisService.expire(redis_key, 1800);
        //写cookie
        CookieUtils.setCookie(request, response, "TT_TOKEN", token);
        return new Result(true, token);
    }

    @Override
    public Result getUserByToken(String token) {
        //根据token获取用户信息
        String key = REDIS_SESSION_KEY + ":" + token;
        String json = redisService.get(key);
        //判断是否查询到结果
        if (StringUtils.isEmpty(json)) {
            return new Result(false, "用户Session已过期");
        }
        TaoUser user = JsonUtils.json2Obj(json, TaoUser.class);
        //更新 Session的过期时间
        redisService.expire(key, REDIS_SESSION_EXPIRE);
        return new Result<>(true, user);
    }

    @Override
    public Result logout(String token) {
        String key = REDIS_SESSION_KEY + ":" + token;
        long record = redisService.del(key);
        return new Result(true, record);
    }
}
