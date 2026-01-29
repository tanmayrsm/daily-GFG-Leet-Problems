import java.util.PriorityQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class ScheduleJobs{
    public static void main(String[] args) {

        class Process {
            String name;
            int schedule;
            Process(String name, int schedule) {
                this.name = name;
                this.schedule = schedule;
            }
        }
        class ProduceConsume {
            BlockingQueue<Process> processes;
            int capacity;
            ProduceConsume(int capacity) {
                processes = new LinkedBlockingQueue(capacity);
                this.capacity = capacity;
            }
            public void produce(Process p) {
                try {
                    while (processes.size() == this.capacity) {
                        
                    }
                    Thread.sleep(2000); // start implemntation
                    System.out.println("Produced :: " + p.name);
                    processes.offer(p);
                } catch (InterruptedException e) {
                    System.err.println("Error while adding ::" + e.getMessage());
                }
            }
    
            public void consume() {
                try {
                    Process p = processes.take();
                    System.out.println("Consumed :: " + p.name);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.err.println("Error while adding ::" + e.getMessage());
                }
            }
        }
        Process[] processes = new Process[] {new Process("P1", 3), new Process("P2", 2), new Process("P3", 20), new Process("P4", 13),new Process("P5", 12)};
        int days = 0, totalDays = 5;
        PriorityQueue<Process> pq = new PriorityQueue<>((Process a, Process b) -> Integer.compare(a.schedule, b.schedule));
        for (Process process : processes)
            pq.offer(process);
            
        PriorityQueue<Process> pq2 = new PriorityQueue<>(pq);
        ProduceConsume produceConsume = new ProduceConsume(3);
        while (days < totalDays) {
            while (!pq.isEmpty()) {
                Process process = pq.poll();
                if (process == null) {
                    break; // Exit if poll() returns null
                }
                try {
                    // Create and start producer and consumer threads
                    Thread t1 = new Thread(() -> produceConsume.produce(process));
                    Thread t2 = new Thread(produceConsume::consume);
                    t1.start();
                    t2.start();

                    // Wait for threads to complete
                    t1.join();
                    t2.join();
                    System.out.println("Waiting for both threads to complete...");
                } catch (InterruptedException e) {
                    System.err.println("Error: " + e.getMessage());
                    Thread.currentThread().interrupt(); // Restore the interrupt status
                }
            }
            // Reset the priority queue for the next day
            System.out.println("Going to next day -------------");
            pq = new PriorityQueue<>(pq2);
            days++;
        }

    }
}

// Design and implement a Job Scheduling library with In-Memory DB. Multiple processes should be able to schedule and run the jobs parallely with the limit for no of jobs can be executed per process in configurable way.

// Requirement
// ------------
// 1. APIs to schedule and monitor the job (P0)
// 2. Implementation of the Job execution should not use any open source library such as spring.