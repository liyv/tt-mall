package com.liyv.sso.controller;

import com.liyv.sso.service.LoginService;
import com.liyv.taotao.dto.Result;
import com.liyv.taotao.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    @ResponseBody
    public Result login(String username, String password, HttpServletRequest request,
                        HttpServletResponse response) {
        try {
            return loginService.login(username, password, request, response);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "密码或用户名错误");
        }
    }

    @GetMapping("/user/token/{token}")
    @ResponseBody
    public Object getUserByToken(@PathVariable String token, String callback) {
        try {
            Result result = loginService.getUserByToken(token);
            if (!StringUtils.isEmpty(callback)) {
                String json = JsonUtils.objectToJson(result);
                json = callback + "(" + json + ")";
                return json;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(false, "获取用户信息失败!");
        }

    }

    @GetMapping("/user/logout/{token}")
    @ResponseBody
    public Object logout(@PathVariable String token, String callback) {
        try {
            Result result = loginService.logout(token);
            if (!StringUtils.isEmpty(callback)) {
                String json = JsonUtils.objectToJson(result);
                json = callback + "(" + json + ")";
                return json;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(false, "退出登录失败!");
        }
    }

}
