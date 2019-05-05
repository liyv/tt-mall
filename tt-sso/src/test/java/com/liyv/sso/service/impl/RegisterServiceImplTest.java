package com.liyv.sso.service.impl;

import com.liyv.sso.service.BaseTest;
import com.liyv.sso.service.RegisterService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class RegisterServiceImplTest extends BaseTest {

    @Autowired
    RegisterService registerService;

    @Test
    public void checkData() {

        int rows = registerService.checkData("liv", 1);
        System.out.println(rows);
    }
}