package com.example.demo.deadlock;

public class B {
    private static B b = null;
    private B() {
        method();
    }

    public static B getInstance() {
        System.out.println("Class B - 1; Thread - " + Thread.currentThread().getName());
        if (b == null) {
            System.out.println("Class B - 2; Thread - " + Thread.currentThread().getName());
            createInstance();
        }
        return b;
    }

    private static synchronized void createInstance() {
        System.out.println("Class B - 3; Thread - " + Thread.currentThread().getName());
        if (b == null) {
            System.out.println("Class B - 4; Thread - " + Thread.currentThread().getName());
            b = new B();
        }
        System.out.println("Class B - 7; Thread - " + Thread.currentThread().getName() + "; " + b);
    }

    private void method() {
        System.out.println("Class B - 5; Thread - " + Thread.currentThread().getName() + "; Method - 1");
        A.getInstance();
        try {
            Thread.sleep(5000);
            //this.wait(5000);
        } catch (Exception exp) {}
        System.out.println("Class B - 6; Thread - " + Thread.currentThread().getName() + "; Method - 2");
    }
}