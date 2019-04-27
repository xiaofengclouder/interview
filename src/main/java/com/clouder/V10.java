package com.clouder;

/**
 * @author: Administrator
 * @date: 2019/4/25
 */
public class V10 {
    public static void main(String[] args) {
        //single thread
        //System.out.println(Single.getInstance() == Single.getInstance());
        //System.out.println(Single.getInstance() == Single.getInstance());
        //System.out.println(Single.getInstance() == Single.getInstance());

        //multiple threads
        for (int i = 0; i < 7; i++) {
            new Thread(Single::getInstance, String.valueOf(i)).start();
        }
    }
}

class Single {
    private static Single singleInstance = null;

    public Single() {
        System.out.println(Thread.currentThread().getName() + " singleInstance constructor");
    }

    //not safe
    /*public static Single getInstance() {
        if (singleInstance == null) {
            singleInstance = new Single();
        }
        return singleInstance;
    }*/

    //safe method 1
/*    public synchronized static Single getInstance() {
        if (singleInstance == null) {
            singleInstance = new Single();
        }
        return singleInstance;
    }*/

    //DCL  safe method 2  【不一定安全，因为可能发生指令重排】
    public static Single getInstance() {
        if (singleInstance == null) {
            synchronized (Single.class) {
                if (singleInstance == null) {
                    singleInstance = new Single();
                }
            }
        }
        return singleInstance;
    }
}
