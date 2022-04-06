package forkjoin.parallelmax;

import java.util.*;
import java.util.concurrent.ForkJoinPool;

public class App {
    public static void main(String[] args) {
        long[] num = createNumbers(90000000);

        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        SequentialMaxFinding sequential = new SequentialMaxFinding();
        long start = System.currentTimeMillis();
        System.out.println("max: " + sequential.max(num));
        System.out.println("Sequential Time: " + (System.currentTimeMillis() - start));

        ParallelMaxTask parallel = new ParallelMaxTask(num, 0, num.length);
        start = System.currentTimeMillis();
        System.out.println("max: " + pool.invoke(parallel));
        System.out.println("Parellel Time: " + (System.currentTimeMillis() - start));
    }

    private static long[] createNumbers(int n) {
        Random random = new Random();
        long[] nums = new long[n];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(900000);
        }
        return nums;
    }
}
