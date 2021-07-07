package com.yrxy.thread.common;

/**
 * Filename: PageHelper.java
 * 
 * @Copyright: Copyright (c)2018
 * @author:
 * @function:分页对象，用作数据的批量处理
 * @version: 1.0
 * @Create at: 2018年8月11日 下午3:59:18
 */

public class PageHelper {

//    public static final int pageSize = 100000;
      public static final int pageSize = 10000;

    private int currentPageNum;
    private int lastPageNum;
    private int totalRecordCount;

    public static void main(String[] args) {
        String tableName="user";
        int index=1;
        long currentPageNum=0;
        String pageSql=PageHelper.getPageSql( tableName,  currentPageNum);
        System.out.println("  pageSql  is  : "+pageSql);

    }

    /**
     *
     * @param totalRecordCount
     * @return
     */
    public static int getTotalPageCount(int totalRecordCount) {
        if (totalRecordCount == 0) {
            return 0;
        }

        int lastPageCount = totalRecordCount % pageSize;
        int totalPageCount;
        if (lastPageCount < pageSize && lastPageCount > 0) {
            totalPageCount = totalRecordCount / pageSize + 1;
        } else {
            totalPageCount = totalRecordCount / pageSize;
        }
        return totalPageCount;
    }


    /**
     *
     * @param currentPageNum
     * @return
     */
    public static String getPageSql(String tableName,long currentPageNum){
//      String pageSql="select * from "+ tableName + " where id>=" +index+(currentPageNum * pageSize）+"  and id<="+ (currentPageNum+1) * pageSize;
        String pageSql="select * from "+ tableName + " where id>=" +(1+(currentPageNum * pageSize)) +" and id<="+(currentPageNum+1) * pageSize;
      return pageSql;
    }


    /**
     * 
     * @param tableName
     * @param updateTimeLabel
     * @param lastMaxUpdateTime
     * @param interval
     * @param currentPageNum
     * @param lastPageNum
     * @return
     */
    public String getPageSql(String tableName, String updateTimeLabel, String lastMaxUpdateTime, String interval, int currentPageNum, int lastPageNum) {
        String pageSql;
        if (lastMaxUpdateTime.indexOf(Constants.sysdate) != -1) {
            pageSql = "select * from(select rownum AS rowno, a.* from " + tableName + " a where " + updateTimeLabel + " >=" + lastMaxUpdateTime + "-" + interval + " and rownum <=" + currentPageNum * pageSize + " order by "+updateTimeLabel+ " ) b where b.rowno >" + lastPageNum * pageSize;

        } else {
            pageSql = "select * from(select rownum AS rowno, a.* from " + tableName + " a where " + updateTimeLabel + ">=to_date('" + lastMaxUpdateTime + "','yyyy-MM-dd HH24:mi:ss')" + "-" + interval + " and rownum <=" + currentPageNum * pageSize + " order by "+updateTimeLabel+  ") b where b.rowno >" + lastPageNum * pageSize;

        }
        return pageSql;
    }

    /**
     * 
     * @param basicSql
     * @return
     */
    public String getTotalRecordsCountSql(String basicSql) {
        String totalRecordsCountSql = "select count(*) from " + "(" + basicSql + ")";
        return totalRecordsCountSql;
    }

    /**
     * 
     * @param tableName
     * @param updateTimeKey
     * @param updateTimeLable
     * @param lastMaxUpdateTime
     * @param interval
     * @return
     */
    public String buildDynamicSyncSql(String tableName, String updateTimeKey, String updateTimeLable, String lastMaxUpdateTime, String interval) {
        String sql;
        if (lastMaxUpdateTime.indexOf(Constants.sysdate) != -1) {
            sql = "SELECT  * FROM  " + tableName + "   where " + updateTimeLable + ">=" + lastMaxUpdateTime + "-" + interval;
        } else {
            sql = "SELECT  * FROM  " + tableName + "   where " + updateTimeLable + ">=to_date('" + lastMaxUpdateTime + "','yyyy-MM-dd HH24:mi:ss')" + "-" + interval;
        }

        return sql;
    }

    public int getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum;
    }

    public int getLastPageNum() {
        return lastPageNum;
    }

    public void setLastPageNum(int lastPageNum) {
        this.lastPageNum = lastPageNum;
    }

    public int getTotalRecordCount() {
        return totalRecordCount;
    }

    public void setTotalRecordCount(int totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }

    public static int getPagesize() {
        return pageSize;
    }



}
