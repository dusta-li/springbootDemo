package com.imooc.order.utils;

import java.util.Random;

public class KeyUtil {

    /**
     * 生成唯一的主键
     */
    public static synchronized String genUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(90000) + 100000;
        return System.currentTimeMillis()+String.valueOf(number);
    }
}
