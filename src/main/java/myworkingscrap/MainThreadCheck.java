package myworkingscrap;




class DaemonWorker implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello world Daemon is executed...");
        }
}

class NormalWorker implements Runnable {
    @Override
    public void run() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Hello world normal thread is completed");
    }
}
public class MainThreadCheck {

    public static void main(String[] args) {

        Thread t1 = new Thread(new DaemonWorker());
        Thread t2 = new Thread(new NormalWorker());

        t1.setDaemon(true);
        System.out.println("Threads are started");
        t1.start();
        t2.start();

    }
}
