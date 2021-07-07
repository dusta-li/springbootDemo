package com.yrxy.thread.case4;

/**
 * Filename:     Counter4.java 
 * @Copyright:   Copyright (c)2017 
 * @Company:     jd 
 * @author:      huanglaoxie
 * @function:
 * @version:     1.0 
 * @Create at:   2017年6月7日 下午3:13:57 
 */
public class Counter3 {
	
	public static int inc = 0;
    public synchronized void increase() {
            inc++;
    }
 
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            inc = 0;
            count();
        }

    }

    private static void count() throws InterruptedException {
        final Counter3 test = new Counter3();
        Thread  th;
        long t1 = System.currentTimeMillis();
        for(int i=0;i<1000;i++){
            th= new Thread(){
                public void run() {
                    for(int j=0;j<100000;j++)
                        test.increase();
                };
            };
            th.start();
            th.join();
        }

        long t2 = System.currentTimeMillis();
        System.out.println("Counter3, "+String.format("结果：%s,耗时(ms)：%s", test.inc, (t2 - t1)));
    }
}
