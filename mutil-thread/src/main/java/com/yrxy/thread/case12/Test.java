package com.yrxy.thread.case12;

import java.util.concurrent.ExecutionException;

/**
 * @author huanglaoxie(微信:yfct-8888)
 * @className Test
 * @description：
 * @date 2017/12/27 20:06
 */
public class Test {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //为了模拟测试方便，事先建好了目录，D:\test\order，如果没有建立的，请建立。否则会报错。
        String path = "D:\\test\\order";
        String keyword1 = "error";
        int c1 = ConcurrentFileSearchTask.statKeyword(path, keyword1);
        System.out.println("订单团队包含关键字"+keyword1+"的个数为：" + c1);

        String keyword2 = "exception";
        int c2 = ConcurrentFileSearchTask.statKeyword(path, keyword2);
        System.out.println("订单团队包含关键字"+keyword2+"的个数为：" + c2);

        String keyword3 = "warn";
        int c3 = ConcurrentFileSearchTask. statKeyword(path, keyword3);
        System.out.println("订单团队包含关键字"+keyword3+"的个数为：" + c3);

    }

}
