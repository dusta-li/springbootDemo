package com.yrxy.thread.case6;

/**
 * @author huanglaoxie(微信:yfct-8888)
 * @className Test
 * @description：
 * @date 2017/12/20 10:17
 */
public class Test {

    public static void main(String[] args) {
        Account account = new Account();
        account.openAccount("黄老邪", 10000);

        Deposit p1 = new Deposit();
        p1.deposit(account, 100);
        Thread depositThread = new Thread(p1, "张三");

        Withdraw p2 = new Withdraw();
        p2.withdraw(account, 100);
        Thread withdrawThread1 = new Thread(p2, "李四");

        Withdraw p3 = new Withdraw();
        p3.withdraw(account, 200);
        Thread withdrawThread2 = new Thread(p3, "王五");
        withdrawThread1.start();
        withdrawThread2.start();
        depositThread.start();


    }

}
