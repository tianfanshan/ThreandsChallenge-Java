package com.shan;

import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {


        BankAccount account = new BankAccount(1000.00, "acc1");


        Thread trThread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                account.deposit(300.00);
                account.withDraw(50.00);
            }
        });

        Thread trThread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                account.deposit(203.75);
                account.withDraw(100.00);
            }
        });


        trThread1.start();
        trThread2.start();

    }
}

