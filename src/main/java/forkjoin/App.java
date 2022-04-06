package forkjoin;

import java.util.concurrent.ForkJoinPool;

public class App {
    public static void main(String[] args) {

        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        SimpleRecursiveAction action = new SimpleRecursiveAction(240);
        action.invoke();
        System.out.println("_____________________________________");
        SimpleRecursiveTask task = new SimpleRecursiveTask(300);
        System.out.println(task.invoke());
    }
}
