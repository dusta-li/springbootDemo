package com.yrxy.thread.case4;

import java.util.concurrent.atomic.AtomicLong;

/** 
 * Filename:     Counter5.java 
 * @Copyright:   Copyright (c)2017 
 * @Company:     jd 
 * @author:      huanglaoxie
 * @function:
 * @version:     1.0 
 * @Create at:   2017年6月7日 下午3:18:24 
 */
public class Counter5 {

	public static AtomicLong inc = new AtomicLong();
	 
    public  void increase() {
        inc.getAndIncrement();
    }
 
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            inc.set(0);
            count();
        }
    }

    private static void count() throws InterruptedException {
        final Counter5 test = new Counter5();
        Thread  th = null;
        long t1 = System.currentTimeMillis();
        for(int i=0;i<1000;i++){
        	th= new Thread(){
                public void run() {
                    for(int j=0;j<100000;j++)
                        test.increase();
                };
            };
            th.start();
//            th.join();
            while(th.isAlive()){
                Thread.sleep(1);
            }
        }

//        while(th.isAlive()){
//        	Thread.sleep(10);
//        }

        long t2 = System.currentTimeMillis();
        System.out.println("Counter5, "+String.format("结果：%s,耗时(ms)：%s", test.inc, (t2 - t1)));
    }

}
