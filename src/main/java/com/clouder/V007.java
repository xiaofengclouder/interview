package com.clouder;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Administrator
 * @date: 2019/4/25
 */
public class V007 {
    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger();
        int addAddGet = integer.incrementAndGet();
        int decDecGet = integer.decrementAndGet();
        System.out.println("addAddGet = " + addAddGet);
        System.out.println("decDecGet = " + decDecGet);

        int getAddAdd = integer.getAndIncrement();
        int getDecDec = integer.getAndDecrement();
        System.out.println("getAddAdd = " + getAddAdd);
        System.out.println("getDecDec = " + getDecDec);

        int addAndGet = integer.addAndGet(1314);
        System.out.println("addAndGet = " + addAndGet);
    }
}
