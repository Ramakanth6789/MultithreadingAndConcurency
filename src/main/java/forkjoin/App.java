package forkjoin;

import java.util.concurrent.ForkJoinPool;

public class App {
    public static void main(String[] args) {

        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        SimpleRecursiveAction action = new SimpleRecursiveAction(2400);
        action.invoke();

    }
}
