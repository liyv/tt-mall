package com.liyv.portal.service.impl;

import com.liyv.portal.BaseTest;
import com.liyv.portal.service.ContentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class ContentServiceImplTest extends BaseTest {

    @Autowired
    ContentService contentService;

    @Test
    public void getAdList() {

        String result = contentService.getAdList();
        System.out.println(result);
    }
}