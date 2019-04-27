package com.clouder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Administrator
 * @date: 2019/4/24
 */
public class V005 {
    public static void main(String[] args) {

        Data data = new Data();
        AtomicData atomic = new AtomicData();

/*        for (int i = 1; i <= 100; i++) {
            final int number = i;
            new Thread(() -> {
                if (data.number == 0) {
                    data.addNum(number);
                    System.out.println("data.number = " + data.number);
                }
            }, i + "").start();
        }*/

        //原子性测试
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    data.addOne();
                    //data.synAddOne();
                    //data.lockAddOne();
                    atomic.addOne();
                }
            }, String.valueOf(i)).start();
        }

        while (Thread.activeCount() > 2) {
            //yield()的作用是让步。它能让当前线程由“运行状态”进入到“就绪状态”，从而让其它具有相同优先级的等待线程获取执行权；但是，并不能保
            //证在当前线程调用yield()之后，其它具有相同优先级的线程就一定能获得执行权；也有可能是当前线程又进入到“运行状态”继续运行！
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + " int final number = " + data.number);
        System.out.println(Thread.currentThread().getName() + " atomic integer final number = " + atomic.integer);

    }

}

class AtomicData {
    AtomicInteger integer = new AtomicInteger();

    void addOne() {
        this.integer.addAndGet(1);
    }
}
