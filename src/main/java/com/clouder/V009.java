package com.clouder;

import java.util.concurrent.TimeUnit;

/**
 * @author: Administrator
 * @date: 2019/4/25
 */
public class V009 {
    public static void main(String[] args) {

        test test = new test();

        for (int i = 0; i < 7; i++) {
            new Thread(test::method1).start();
            new Thread(test::method2).start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class test {
    int a = 0;
    boolean flag = false;

    void method1() {
        a = 1;
        flag = true;
    }

    void method2() {
        a += 1;
        System.out.println(Thread.currentThread().getName() + " a = " + a);
    }
}
