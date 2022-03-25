package myworkingscrap;


import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ManagerWorkerTask {
    static ExecutorService service= Executors.newFixedThreadPool(3);
    static CopyOnWriteArrayList<Integer> taskNums=new CopyOnWriteArrayList<>();
    /*public void run(){
        taskNums.add(15);
    }*/

    public static void main(String[] args) {
        taskNums.add(10);
        taskNums.add(12);
        taskNums.add(14);
        //ManagerWorkerTask t1=new ManagerWorkerTask();
        Runnable taskAddition=()->{
            try {
                System.out.println(Thread.currentThread().getName());
                taskNums.add(15);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        service.execute(taskAddition);
        service.execute(taskAddition);
        service.execute(taskAddition);
        service.execute(taskAddition);
        service.execute(taskAddition);
        taskNums.stream().forEach(i-> System.out.println(i));
        service.shutdown();

    }
}