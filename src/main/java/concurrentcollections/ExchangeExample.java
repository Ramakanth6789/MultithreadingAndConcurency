package concurrentcollections;

import java.util.concurrent.Exchanger;

class FirstExchangeWorker implements Runnable {
    private int counter;
    private Exchanger<Integer> exchanger;

    public FirstExchangeWorker(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }
    @Override
    public void run() {
        while (true) {
            counter++;
            System.out.println("FirstExchangeWorker is incremented to " + counter);

            try {
                counter = exchanger.exchange(counter);
                System.out.println("FirstExchangeWorker received counter: " + counter);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class SecondExchangeWorker implements Runnable {
    private int counter;
    private Exchanger<Integer> exchanger;

    public SecondExchangeWorker(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }
    @Override
    public void run() {
        while (true) {
            counter--;
            System.out.println("SecondExchangeWorker is decremented to " + counter);

            try {
                counter = exchanger.exchange(counter);
                System.out.println("SecondExchangeWorker received counter: " + counter);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class ExchangeExample {
    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();
        FirstExchangeWorker first = new FirstExchangeWorker(exchanger);
        SecondExchangeWorker second = new SecondExchangeWorker(exchanger);
        new Thread(first).start();
        new Thread(second).start();
    }
}
