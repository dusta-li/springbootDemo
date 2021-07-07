package com.yrxy.thread.case1;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author huanglaoxie(微信:yfct-8888)
 * @className Producer
 * @description：
 * @date 2017/11/25 14:55
 */
public class Producer {

    public static void main(String[] args) {
        Producer.createData();
    }

    public static void createData(){
        ExecutorService pool = Executors.newFixedThreadPool(100);
        int totalPageNo=50;
//        int pageSize = 10;
        int pageSize = 20000;
        //共10w条数据，每页5000条数据，20个线程
        long start=System.currentTimeMillis();
         AtomicInteger  atomicInt = new AtomicInteger();
        for(int currentPageNo = 0; currentPageNo <totalPageNo; currentPageNo++){
            int finalCurrentPageNo = currentPageNo;
            int finalCurrentPageNo1 = currentPageNo;
            Runnable run = new Runnable() {
                public void run(){
                    List    userList=new ArrayList<>();
                    for(int i=1;i<=pageSize;i++){
                        int id=i+ finalCurrentPageNo *pageSize;
                        User  user =new User();
                        user.setId(id);
                        user.setName("huanglaoxie:"+id);
                        userList.add(user);

                    }

                    atomicInt.addAndGet( UserBatchHandler.batchSave(userList));

                    if(atomicInt.get() ==(totalPageNo*pageSize)){
                            System.out.println("  sync data to db, it  has spent " +(System.currentTimeMillis()-start)+"  ms");
                        }

                }
            };
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            pool.execute(run);
        }

    }

}
