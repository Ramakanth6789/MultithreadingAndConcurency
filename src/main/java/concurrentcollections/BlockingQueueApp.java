package concurrentcollections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class FirstWorker implements Runnable {
    private BlockingQueue<Integer> blockingQueue;

    public FirstWorker(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        int counter = 0;
        while(true){
            try {
                blockingQueue.put(counter);
                System.out.println("Putting counter in the queue: " + counter);
                counter++;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
}

class SecondWorker implements Runnable {
    private BlockingQueue<Integer> blockingQueue;

    public SecondWorker(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {

        while(true){
            try {
                int value =  blockingQueue.take();
                System.out.println("Taking counter in the queue: " + value);
                Thread.sleep(2000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
}
public class BlockingQueueApp {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        FirstWorker firstWorker = new FirstWorker(queue);
        SecondWorker secondWorker = new SecondWorker(queue);

        new Thread(secondWorker).start();
        new Thread(firstWorker).start();

    }
}
