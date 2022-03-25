package singlethreadexecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class TaskPool implements Runnable{
    private int id;
    public TaskPool(int id) {
        this.id = id;
    }
    @Override
    public void run() {
        System.out.println("Task with id " + id + " is in work - thread id: " + Thread.currentThread().getName());
        long duration = (long) Math.random() * 5;
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
public class FixedThreadpool{
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for(int i=0; i<100; i++){
            executorService.execute(new TaskPool(i));
        }
        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(1000, TimeUnit.MILLISECONDS)) {

            }
        } catch (InterruptedException e) {
            executorService.shutdown();
        }
    }
}