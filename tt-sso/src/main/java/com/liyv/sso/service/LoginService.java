package com.liyv.sso.service;

import com.liyv.taotao.dto.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {

    Result login(String username, String password, HttpServletRequest request, HttpServletResponse response);

    Result getUserByToken(String token);

    Result logout(String token);
}
