package com.yrxy.thread.case5;
/**
 * @author huanglaoxie(微信:yfct-8888)
 * @className ComputeWorker
 * @description：
 * @date 2017/11/26 11:58
 */
public class ComputeWorker extends Worker {

public Object handle(Object input) {
	try {
		//模拟执行校验逻辑
		Thread.sleep(5);
	} catch (InterruptedException e) {

		e.printStackTrace();
	}
	Long i = (Long) input;
	return  i*i;
}
}
