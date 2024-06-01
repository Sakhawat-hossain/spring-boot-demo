package com.example.demo.deadlock;

public class A {
    private static A a = null;
    private A() {
        method();
    }

    public static A getInstance() {
        System.out.println("Class A - 1; Thread - " + Thread.currentThread().getName());
        if (a == null) {
            System.out.println("Class A - 2; Thread - " + Thread.currentThread().getName());
            createInstance();
        }
        return a;
    }

    private static synchronized void createInstance() {
        System.out.println("Class A - 3; Thread - " + Thread.currentThread().getName());
        if (a == null) {
            System.out.println("Class A - 4; Thread - " + Thread.currentThread().getName());
            a = new A();
            B.getInstance();
        }
        System.out.println("Class A - 7; Thread - " + Thread.currentThread().getName());
    }

    private void method() {
        System.out.println("Class A - 5; Thread - " + Thread.currentThread().getName() + "; Method - 1");
        try {
            Thread.sleep(10000);
            //this.wait(10000);
        } catch (Exception exp) {}
        System.out.println("Class A - 6; Thread - " + Thread.currentThread().getName() + "; Method - 2");
    }
}
