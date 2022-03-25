class Runner1 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10; ++i) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Runner1: " + i);
        }
    }
}

class Runner2 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10; ++i) {
            System.out.println("Runner2: " + i);
        }
    }
}

public class Multithreading {
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Runner1();
        Thread t2 = new Runner2();
//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; ++i) {
//                    System.out.println("Runner1: " + i);
//                }
//            }
//        });
//
//        Thread t2 = new Thread(() -> {
//            for (int i = 0; i < 10; ++i) {
//                System.out.println("Runner 2: " + i);
//            }
//        });
         t1.start();
         t2.join();
         t2.start();

         t1.join();
        System.out.println("Threads finished");
    }
}
