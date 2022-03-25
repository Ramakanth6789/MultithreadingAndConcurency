package producerconsumer;

import java.util.ArrayList;
import java.util.List;

class ProducerConsumer{
    private static final List<Integer> list = new ArrayList<>();
    private static final int UPPER_LIMIT = 5;
    private static final int LOWER_LIMIT = 0;
    private static int value=0;
    private final Object lock = new Object();


    public void producer() throws InterruptedException {

        synchronized (lock){
            while(true){
                if (list.size() == 5) {
                    System.out.println("MAX SIZE REACHED: Waiting for objects to be removed");
                    lock.wait();
                } else {
                    System.out.println("Adding value "+ value);
                    list.add(value);
                    value++;
                    lock.notify();
                }
                Thread.sleep(1000);
            }

        }

    }

    public void consumer() throws InterruptedException {

        synchronized (lock){
            while (true) {
                if (list.size() == 0) {
                    System.out.println("List is empty: Waiting for objects to be added");
                    value=0;
                    lock.wait();
                } else{
                    System.out.println("Number is deleted from the list: "+list.remove(list.size()-1));
                    lock.notify();
                }
                Thread.sleep(1000);
            }

        }
    }
}
public class App {
    public static void main(String[] args) {
        ProducerConsumer producerConsumer = new ProducerConsumer();

        Thread producer = new Thread(()->{
            try {
                producerConsumer.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumer = new Thread(()->{
            try {
                producerConsumer.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();
    }
}
