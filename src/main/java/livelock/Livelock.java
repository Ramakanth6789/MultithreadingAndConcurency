package livelock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Livelock {
    private static final Lock lock1 = new ReentrantLock();
    private static final Lock lock2 = new ReentrantLock();

    public void worker1(){
        while(true){
            try {
                lock1.tryLock(50, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("worker1 acquire lock1..");
            System.out.println("worker1 trying to acquire lock2...");

            if (lock2.tryLock()) {
                System.out.println("worker1 acquired lock2..");
                lock1.unlock();
            }else {
                System.out.println("worker1 did not acquire lock2");
                continue;
            }
            break;
        }
    }

    public void worker2(){
        while(true){
            try {
                lock2.tryLock(50, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("worker2 acquire lock2..");
            System.out.println("worker2 trying to acquire lock1...");

            if (lock1.tryLock()) {
                System.out.println("worker2 acquired lock1..");
                lock1.unlock();
            }else {
                System.out.println("worker2 did not acquire lock1");
                continue;
            }
            break;
        }
    }

    public static void main(String[] args) {
        Livelock livelock = new Livelock();

        new Thread(livelock::worker1,"t1").start();
        new Thread(livelock::worker2,"t2").start();
    }
}
