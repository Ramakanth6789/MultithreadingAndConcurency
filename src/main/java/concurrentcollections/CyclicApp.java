package concurrentcollections;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class WorkerClass implements Runnable {
    private int id;
    private Random random;
    private CyclicBarrier cyclicBarrier;

    public WorkerClass(int id, CyclicBarrier cyclicBarrier) {
        this.id = id;
        this.cyclicBarrier = cyclicBarrier;
        this.random = new Random();
    }

    @Override
    public void run() {
        doWork();
    }

    private void doWork() {
        System.out.println("Thread with ID: " + id + " : "+ Thread.currentThread().getName()+" starts the task...");
        try {
            Thread.sleep(random.nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread is ID: " + id + " is finished..");

        try {

            cyclicBarrier.await();
            System.out.println("After await()");
        } catch (InterruptedException |BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
    public String toString() {
        return ""+this.id;
    }

}

public class CyclicApp {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("All tasks are finished");

            }
        });

        for (int i = 0; i < 5; i++) {
            executorService.execute(new WorkerClass(i+1,cyclicBarrier));
        }

        executorService.shutdown();
    }

}
