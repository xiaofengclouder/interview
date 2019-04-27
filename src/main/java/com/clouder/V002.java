package com.clouder;

/**
 * @author: Administrator
 * @date: 2019/4/24
 */

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * volatile
 * java虚拟机提供的轻量级的同步机制：
 * 1. 保证可见性
 * 2. 不保证原子性
 * 3. 禁止指令重排
 * <p>
 * JVM Java Virtual Machine
 * JMM Java Memory Model java内存模型
 */
public class V002 {
    public static void main(String[] args) {
        Data data = new Data();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " in...");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.addTo60();
            System.out.println(Thread.currentThread().getName() + " out... number = " + data.number);
        }, "AAA").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " number = " + data.number);
            try {
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " number = " + data.number);
        }, "BBB").start();

        //如果main线程没有被通知到number已经被修改，将一直停留在while循环中
        int count = 0;
        while (data.number == 0) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(++count);
        }

        System.out.println("main number = " + data.number);
    }
}

class Data {
    //int number = 0;
    volatile int number = 0;

    void addTo60() {
        this.number = 60;
    }

    void addNum(int number) {
        this.number += number;
    }

    void addOne() {
        this.number++;
    }

    synchronized void synAddOne() {
        this.number++;
    }

    Lock lock = new ReentrantLock();

    void lockAddOne() {
        lock.lock();
        //可以没有判断???
        this.number++;
        lock.unlock();
    }
}