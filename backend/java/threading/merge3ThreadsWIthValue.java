import java.util.*;
import java.util.concurrent.*;

class Main {

    // Executor to run worker tasks
    static ExecutorService executor;

    // Merged thread now takes a list of values and computes final value
    static class Merged extends Thread {
        List<Integer> values;
        int finalValue;

        Merged(List<Integer> values) {
            this.values = values;
        }

        public void run() {
            try {
                // Example merge logic: sum all values
                int sum = 0;
                for (int v : values) {
                    sum += v;
                }
                this.finalValue = sum;
                System.out.println("running thread :: merged :: finalValue = " + this.finalValue);
            } catch (Exception e) {
                System.out.println("merged :: error " + e);
            }
        }

        int getFinalValue() {
            return finalValue;
        }
    }

    // Mask is now a Callable that returns its val
    static class Mask implements Callable<Integer> {
        int id, ts, val;

        Mask(int id, int ts, int val) {
            this.id = id;
            this.ts = ts;
            this.val = val;
        }

        @Override
        public Integer call() throws Exception {
            try {
                System.out.println("starting ::" + this.id);
                Thread.sleep(this.ts);
                System.out.println("done ::" + this.id + "::" + this.val);
                return this.val;
            } catch (Exception e) {
                System.out.println("error in " + this.id + "::" + e);
                throw e;
            }
        }
    }

    public static void main(String[] args) {
        int n = 3;
        executor = Executors.newFixedThreadPool(n);

        try {
            // Submit all Mask tasks and collect Futures
            List<Future<Integer>> futures = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                Mask task = new Mask(i, 1000, i * 10);
                futures.add(executor.submit(task));  // submit returns a Future [web:10][web:13]
            }

            // Wait for all tasks and collect values
            List<Integer> values = new ArrayList<>();
            for (Future<Integer> f : futures) {
                try {
                    Integer v = f.get(); // blocks until task is done [web:13]
                    values.add(v);
                } catch (Exception e) {
                    System.out.println("Error getting result: " + e);
                }
            }

            // Start merged thread with collected values
            Merged merged = new Merged(values);
            merged.start();
            merged.join();

            System.out.println("Final merged value = " + merged.getFinalValue());
            System.out.println("Done");
        } finally {
            executor.shutdown(); // always shutdown executor [web:1][web:2]
        }
    }
}
