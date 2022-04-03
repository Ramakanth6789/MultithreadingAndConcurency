package parallellization.sumproblem;

import java.util.*;

public class App {
    public static void main(String[] args) {
        Random random = new Random();

        int[] num = new int[1000000000];
        for (int i = 0; i < num.length; i++) {
            num[i] = random.nextInt(100);
        }
        int n = Runtime.getRuntime().availableProcessors();

        Long start = System.currentTimeMillis();
        SequencialSum sequencialSum = new SequencialSum();
        System.out.println("Sequencial Sum: " + sequencialSum.sumOfArray(num));
        System.out.println("Time: " + (System.currentTimeMillis() - start));
        ParallelSum parallelSum = new ParallelSum(n);
        start = System.currentTimeMillis();
        System.out.println("Parallel sum: " + parallelSum.sum(num));
        System.out.println("Time: " + (System.currentTimeMillis() - start));

    }
}
