package com.yrxy.thread.case2;

import com.yrxy.thread.common.PageHelper;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author huanglaoxie(微信:yfct-8888)
 * @className ExcelExporter
 * @description：
 * @date 2017/11/27 20:34
 */
public class ExcelExporter {

    public static void main(String[] args) {
        createExcelFile();
    }

    public static void createExcelFile(){
        ExecutorService pool = Executors.newFixedThreadPool(100);
        int count= UserHandler.queryCount();
        int totalPageCount=PageHelper.getTotalPageCount(count);
        String tableName="user";

        long start=System.currentTimeMillis();
        for(int currentPageNum=0;currentPageNum<totalPageCount;currentPageNum++) {
           String pageSql= PageHelper.getPageSql(tableName, currentPageNum);
            List userList=UserHandler.queryUserList(pageSql);
            int finalCurrentPageNum = currentPageNum;
            int finalCurrentPageNum1 = currentPageNum;
            Runnable run = new Runnable() {
                @Override
                public void run() {
                 ExcelUtil.CreateExcel(finalCurrentPageNum,userList);
                if(finalCurrentPageNum1 ==(totalPageCount-1)){
                    System.out.println("  export data to excel, it  has spent " +(System.currentTimeMillis()-start)+"  ms");
                }
                }

            };
            pool.execute(run);

        }
    }



}
