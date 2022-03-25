package concurrentcollections;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

class ReadTask implements Runnable {
    private List<Integer> list;

    public ReadTask(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(list);

        }

    }
}

class WriteTask implements Runnable{
    private List<Integer> list;
    private Random random;

    public WriteTask(List<Integer> list) {
        this.list = list;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.set(random.nextInt((list.size())), random.nextInt(10));
        }
    }
}

class  ConcurrencyArray{
    private List<Integer> list;

    public ConcurrencyArray(){
        this.list = new CopyOnWriteArrayList<>();
        this.list.addAll(Arrays.asList(0,0,0,0,0,0,0,0,0,0));
    }
    public void simulate(){
        Thread t1 = new Thread(new WriteTask(list));
        Thread t2 = new Thread(new WriteTask(list));
        Thread t3 = new Thread(new WriteTask(list));
        Thread t4 = new Thread(new ReadTask(list));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }
}

public class CopyOnArrayExample {

    public static void main(String[] args) {
        ConcurrencyArray concurrencyArray = new ConcurrencyArray();
        concurrencyArray.simulate();
    }
}
