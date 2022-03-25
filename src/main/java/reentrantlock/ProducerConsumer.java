package reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Worker{
    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void producer() throws InterruptedException {
        lock.lock();
        System.out.println("Producer method...");
        condition.await();
        System.out.println("Producer method.. again");
        lock.unlock();

    }
    public static void consumer() throws InterruptedException{

        lock.lock();
        Thread.sleep(2000);
        System.out.println("Consumer method...");
        condition.signal();
        lock.unlock();
    }
}

public class ProducerConsumer {


    public static void main(String[] args) {

        Worker worker = new Worker();

        Thread t1 = new Thread(()->{
            try {
                worker.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(()->{
            try {
                worker.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();
        t1.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
