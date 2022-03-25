package myworkingscrap;

public class SynchronizeCheck {

    public static int counter1=0, counter2=0;
    public static final Object lock1 = new Object();
    public static void  increment1(){
        synchronized (lock1){
            counter1++;
        }
    }
    public static void  increment2(){
        synchronized (lock1){
            counter2++;
        }
    }

    public static void process() throws InterruptedException {

        Thread t1 = new Thread(()-> {
            for(int i=0; i<100; i++){
                increment1();
            }
        });

        Thread t2 = new Thread(()-> {
            for(int i=0; i<100;i++){
                increment2();
            }
        });
        t1.start();
        t2.start();
        t1.join();

        System.out.println("done with whole process now  counter value is: " + counter1+" and counter 2: "+ counter2);
    }

    public static void main(String[] args) throws InterruptedException {
        process();
    }


}
