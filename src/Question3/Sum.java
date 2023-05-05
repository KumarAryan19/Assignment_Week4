package Question3;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Sum {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            int sum = 0;
            for (int i = 1; i <= 10; i++) {
                sum += i;
            }
            return sum;
        });

        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
            int sum = 0;
            for (int i = 11; i <= 20; i++) {
                sum += i;
            }
            return sum;
        });

        CompletableFuture<Integer> cf3 = CompletableFuture.supplyAsync(() -> {
            int sum = 0;
            for (int i = 21; i <= 30; i++) {
                sum += i;
            }
            return sum;
        });

        CompletableFuture<Integer> allFutures = CompletableFuture.allOf(cf1, cf2, cf3)
                .thenApply((v) -> cf1.join() + cf2.join() + cf3.join());

        int result = allFutures.get();
        System.out.println("Sum of 1 to 30 integers: " + result);
    }
}
