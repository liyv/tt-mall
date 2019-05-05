package com.liyv.sso.controller;

import com.liyv.sso.service.RegisterService;
import com.liyv.taotao.dto.Result;
import com.liyv.taotao.entity.TaoUser;
import com.liyv.taotao.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @GetMapping("/check/{param}/{type}")
    @ResponseBody
    public Object checkData(@PathVariable String param, @PathVariable int type, String callback) {
        int rows = registerService.checkData(param, type);
        Result result;
        if (rows > 0) {
            //数据存在
            result = new Result(false, "该数据已存在");
        } else {
            result = new Result(true, 0);
        }
        String json = JsonUtils.objectToJson(result);
        if (!StringUtils.isEmpty(callback)) {
            //请求为jsonp调用,需要支持
            json = callback + "(" + json + ")";
        }
        return json;
    }

    @PostMapping("/register")
    @ResponseBody
    public Result register(TaoUser user) {
        return registerService.register(user);
    }
}
