package com.yrxy.thread.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author huanglaoxie(微信:yfct-8888)
 * @className DateUtil
 * @description：
 * @date 2017/12/2 13:28
 */
public class DateUtil {

    /**
     *
      * @return
     */
    public static String  getTodayStr(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr=sdf.format(new Date());
        return dateStr;
    }
}
