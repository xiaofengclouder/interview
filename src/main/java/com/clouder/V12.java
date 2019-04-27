package com.clouder;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Administrator
 * @date: 2019/4/25
 */
public class V12 {
    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(5);

        boolean b = integer.compareAndSet(3, 5);
        System.out.println("b = " + b + " currentValue = " + integer.get());

        System.out.println(integer.compareAndSet(5, 1314) + " currentValue = " + integer.get());
    }
}
