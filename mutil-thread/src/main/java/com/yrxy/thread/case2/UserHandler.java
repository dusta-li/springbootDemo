package com.yrxy.thread.case2;

import com.yrxy.thread.common.DataSourceUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huanglaoxie(微信:yfct-8888)
 * @className QueryUser
 * @description：
 * @date 2017/11/27 17:42
 */
public class UserHandler {

//     public final static long pageSize=10000;

    public static void main(String[] args) {
       System.out.append("count is  : "+ queryCount());
    }

    /**
     *
     * @param pageSql
     * @return
     */
    public static List queryUserList(String pageSql){
        List userList=new ArrayList();
        Connection conn= DataSourceUtils.getConnection();
        ResultSet rst = null;
        User user;

        try {
            PreparedStatement pst = conn.prepareStatement(pageSql);
            rst= pst.executeQuery();
            while(rst.next()) {
                user=new User();
                user.setId((int) rst.getObject("id"));
                user.setName((String) rst.getObject("name"));
                user.setCreatedTime((Timestamp)rst.getObject("createdTime"));
                user.setUpdatedTime((Timestamp)rst.getObject("updatedTime"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public static int queryCount()  {
        String countSql = "SELECT count(*) as count  from user";
        ResultSet rst = null;
        Long count = null;
        Connection conn = null;
        try {
             conn= DataSourceUtils.getConnection();
            PreparedStatement pst = conn.prepareStatement(countSql);
             rst= pst.executeQuery();
            while(rst.next()) {
                count= (Long) rst.getObject("count");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                rst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return count.intValue();
    }

}
