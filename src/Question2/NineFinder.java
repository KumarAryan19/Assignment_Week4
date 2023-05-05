package Question2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class NineFinder extends RecursiveTask<List<Integer>> {
    private static final int THRESHOLD = 100;
    private final int start;
    private final int end;

    public NineFinder(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected List<Integer> compute() {
        if (end - start <= THRESHOLD) {
            List<Integer> result = new ArrayList<>();
            for (int i = start; i <= end; i++) {
                if (String.valueOf(i).contains("9")) {
                    result.add(i);
                }
            }
            return result;
        } else {
            int mid = (start + end) / 2;
            NineFinder left = new NineFinder(start, mid);
            NineFinder right = new NineFinder(mid + 1, end);
            invokeAll(left, right);
            List<Integer> result = new ArrayList<>();
            result.addAll(left.join());
            result.addAll(right.join());
            return result;
        }
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        List<Integer> result = pool.invoke(new NineFinder(1, 1000));
        System.out.println(result);
    }
}

