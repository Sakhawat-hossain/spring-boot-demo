package com.example.demo.deadlock;

public class DeadlockTest {

    public static void main(String[] args) {
        System.out.println("Main Class - 1");

        //A.getInstance();
        //B.getInstance();

        ThreadTest threadTest = new ThreadTest();
        Thread t1 = new Thread(threadTest,"1st Thread");
        Thread t2 = new Thread(threadTest,"2nd Thread");

        t1.start();
        t2.start();

        System.out.println("Main Class - 2");
    }
}

class ThreadTest implements Runnable {
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        if (threadName.startsWith("1st")) {
            A.getInstance();
            //B.getInstance();
        } else {
            //A.getInstance();
            B.getInstance();
        }
    }
}
