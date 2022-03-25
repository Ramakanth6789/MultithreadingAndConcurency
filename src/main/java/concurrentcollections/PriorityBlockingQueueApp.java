package concurrentcollections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class Person implements Comparable<Person> {

    private int age;
    private String name;

    public Person(int age, String name){
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge(){
        return age;
    }

    @Override
    public int compareTo(Person otherPerson) {
        return name.compareTo(otherPerson.getName());
       // return age.compare(otherPerson.getAge());
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

class FirstWorkerClass implements Runnable{
    private BlockingQueue<Person> blockingQueue;

    public FirstWorkerClass(BlockingQueue<Person> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            blockingQueue.put(new Person(21, "Ram"));
            blockingQueue.put(new Person(22, "Silk"));
            blockingQueue.put(new Person(23,"Ding"));
            Thread.sleep(1000);
            blockingQueue.put(new Person(24,"Kong"));
            Thread.sleep(2000);
            blockingQueue.put(new Person(25,"Dil"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class SecondWorkerClass implements Runnable{
    private BlockingQueue<Person> blockingQueue;
    public SecondWorkerClass(BlockingQueue<Person> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            Thread.sleep(2000);
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            Thread.sleep(1000);
            System.out.println(blockingQueue.take());


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class PriorityBlockingQueueApp {
    public static void main(String[] args) {
        BlockingQueue<Person> queue = new PriorityBlockingQueue<Person>();

        new Thread(new FirstWorkerClass(queue)).start();
        new Thread(new SecondWorkerClass(queue)).start();
    }
}
