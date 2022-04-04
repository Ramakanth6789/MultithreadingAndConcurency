package forkjoin;

import java.util.concurrent.RecursiveAction;

public class SimpleRecursiveAction extends RecursiveAction {

    private int simulatedWork;

    public SimpleRecursiveAction(int simulatedWork) {
        this.simulatedWork = simulatedWork;
    }

    @Override
    protected void compute() {
        if (simulatedWork > 100) {
            System.out.println("Parallel execution and split the tasks..." + simulatedWork);
            SimpleRecursiveAction action1 = new SimpleRecursiveAction(simulatedWork / 2);
            SimpleRecursiveAction action2 = new SimpleRecursiveAction(simulatedWork / 2);

            action1.fork();
            action2.fork();
        } else {
            System.out.println("The task is small, so sequential execution is fine.. " + simulatedWork);
        }

    }
}
