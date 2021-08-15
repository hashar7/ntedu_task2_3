package com.company;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Count {

    private int count;
    ReentrantLock locker;
    Condition condition;

    Count() {
        count = 0;
        locker = new ReentrantLock();
        condition = locker.newCondition();
    }

    public void increment(int value, String name) {
        locker.lock();
        try {
            while(count >= Constants.COUNTER_BOUND) {
                condition.await();
            }
            count += value;
            System.out.println("Thread " + name + " Incremented count by value: " + value);
            System.out.println("Current value: " + count);
            condition.signalAll();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            locker.unlock();
        }
    }

    public void decrement(int value, String name) {
        locker.lock();
        try {
            while(count < Constants.COUNTER_BOUND) {
                condition.await();
            }
            count -= value;
            System.out.println("Thread " + name + " Decremented count by value: " + value);
            System.out.println("Current value: " + count);
            condition.signalAll();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            locker.unlock();
        }
    }

    public int getCount() {
        return count;
    }

}

public class Main {

    public static void main(String[] args) {
        Count count = new Count();
        Thread A = new Thread(new A(count));
        Thread B = new Thread(new B(count));
        A.setName("A");
        B.setName("B");
        A.start();
        B.start();
        try {
            A.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Thread A stopped");
        }
        try {
            B.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Thread B stopped");
        }
        System.out.println("Threads stopped");
    }

}

class A implements Runnable {
    Count count;
    A(Count count) {
        this.count = count;
    }
    @Override
    public void run() {
        count.decrement(Constants.COUNTER_BOUND >> 1, Thread.currentThread().getName());
        count.decrement(Constants.COUNTER_BOUND, Thread.currentThread().getName());
    }
}

class B implements Runnable {
    Count count;
    B(Count count) {
        this.count = count;
    }
    @Override
    public void run() {
        int bound = Constants.COUNTER_BOUND + (Constants.COUNTER_BOUND >> 1);
        for(int i = 0; i < bound; i++) {
            count.increment(1, Thread.currentThread().getName());
        }
    }
}

/* OUTPUT:

    Thread B Incremented count by value: 1
    Current value: 1
    Thread B Incremented count by value: 1
    Current value: 2
    Thread B Incremented count by value: 1
    Current value: 3
    Thread B Incremented count by value: 1
    Current value: 4
    Thread B Incremented count by value: 1
    Current value: 5
    Thread B Incremented count by value: 1
    Current value: 6
    Thread B Incremented count by value: 1
    Current value: 7
    Thread B Incremented count by value: 1
    Current value: 8
    Thread B Incremented count by value: 1
    Current value: 9
    Thread B Incremented count by value: 1
    Current value: 10
    Thread A Decremented count by value: 5
    Current value: 5
    Thread B Incremented count by value: 1
    Current value: 6
    Thread B Incremented count by value: 1
    Current value: 7
    Thread B Incremented count by value: 1
    Current value: 8
    Thread B Incremented count by value: 1
    Current value: 9
    Thread B Incremented count by value: 1
    Current value: 10
    Thread A Decremented count by value: 10
    Current value: 0
    Thread A stopped
    Thread B stopped
    Threads stopped

 */