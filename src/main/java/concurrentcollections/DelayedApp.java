package concurrentcollections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedApp {
    public static void main(String[] args) {
        BlockingQueue<DelayedWorker> queue = new DelayQueue<>();

        try {
            queue.put(new DelayedWorker(1000,"First Thread"));
            queue.put(new DelayedWorker(10000, "Second Thread"));
            queue.put(new DelayedWorker(4000, "Third Thread"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!queue.isEmpty()) {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class DelayedWorker implements Delayed{

    private long duration;
    private String message;

    public DelayedWorker(long duration, String message) {
        this.duration = System.currentTimeMillis() + duration;
        this.message = message;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(duration-System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed otherDelayed) {
        if(this.duration <((DelayedWorker) otherDelayed).getDuration()){
            return -1;
        }
        if(this.duration > ((DelayedWorker) otherDelayed).getDuration()){
            return +1;
        }
        return 0;
    }

    public long getDuration() {
        return duration;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "DelayedWorker{" +
                "message='" + message + '\'' +
                '}';
    }
}
