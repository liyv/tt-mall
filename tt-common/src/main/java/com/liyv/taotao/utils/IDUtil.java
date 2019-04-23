package com.liyv.taotao.utils;

import java.util.Random;

public class IDUtil {

    /**
     * 生成图片名称
     */
    public static String genImageName(){
        long millis=System.currentTimeMillis();

        //加上3位随机数
        Random random = new Random();
        int end3=random.nextInt(999);
        String str = millis + String.format("%03d", end3);
        return str;
    }
}
