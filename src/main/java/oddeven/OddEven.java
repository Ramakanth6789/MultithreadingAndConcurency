package oddeven;


public class OddEven {
    public static int counter = 1;
    private static int finalValue;
    private static Object lock = new Object();

    public void oddNumber(){
        synchronized (lock){
            while (counter < finalValue) {
                while (counter % 2 != 0) {
                    //System.out.println("In oddNumber while "+counter);
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.print(counter+" ");
                counter++;
                lock.notify();
            }
        }
    }
    public void evenNumber(){
        synchronized (lock){
            while (counter < finalValue) {
                while (counter % 2 == 0) {
                    //System.out.println("In evenNumber while "+counter);
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.print(counter+" ");
                counter++;

                lock.notify();
            }
        }
    }

    public static void main(String[] args) {

        finalValue = 100;
        OddEven oddEven = new OddEven();

        new Thread(() -> oddEven.oddNumber()).start();
        new Thread(() -> oddEven.evenNumber()).start();




    }
}
