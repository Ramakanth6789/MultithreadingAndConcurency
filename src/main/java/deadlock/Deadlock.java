package deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Deadlock {
    private static final Lock lock1 = new ReentrantLock();
    private static final Lock lock2 = new ReentrantLock();

    public void worker1(){
        lock1.lock();
        System.out.println("worker1 acquires lock1...");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock2.tryLock();
        System.out.println("worker1 acquires lock2..");
        lock1.unlock();
        lock2.unlock();
    }

    public void worker2(){
        lock1.lock();
        System.out.println("worker2 acquires lock2...");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock2.lock();
        System.out.println("worker2 acquires lock1...");
        lock1.unlock();
        lock2.unlock();
    }

    public static void main(String[] args) {
        Deadlock deadlock = new Deadlock();
        new Thread(deadlock::worker1,"worker1").start();
        new Thread(deadlock::worker2,"worker2").start();

    }
}
