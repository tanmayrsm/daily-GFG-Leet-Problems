// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

class Main {
    
    static class ConsumerProducer {
        BlockingQueue<Integer> bq;
        int total;
        ConsumerProducer(int capacity, int total) {
            bq = new ArrayBlockingQueue<>(capacity);
            this.total = total;
        }
        public void produce(int no) {
            try {
                bq.put(no); // blocks when full
                System.out.println("added ::" + no);
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("Error ::" + e);
            }
        }
    
        public void consume() {
            try {
                int no = bq.take(); // blocks when empty
                System.out.println("consumed ::" + no);
                Thread.sleep(4000);
                synchronized (this) {
                    this.total--;
                }
            } catch (Exception e) {
                System.out.println("Error ::" + e);
            }
        }
    }
    static class Worker extends Thread{
        ConsumerProducer cp;
        int no;
        Worker(ConsumerProducer cp, int no) {
            this.cp = cp;
            this.no = no;
        }
        @Override
        public void run() {
            this.cp.produce(no);
        }
    }
    public static void main(String[] args) {
        // producer consumer
        int n = 10;
        Worker[] producer = new Worker[n];
        Thread[] consumer = new Thread[n];
        ConsumerProducer cp = new ConsumerProducer(3, n);
        for(int i = 0; i < n; i++) {
            producer[i] = new Worker(cp, i + 1);
            consumer[i] = new Thread(() -> cp.consume());
        }
        for(int i = 0; i < n; i++) {
            consumer[i].start();
            producer[i].start();
        }
        for(int i = 0; i < n; i++) {
            try {
                consumer[i].join();
                producer[i].join();
            } catch (Exception e) {
                System.out.println("error ::" + e);
            }
        }
        System.out.println("done");
        
    }
}