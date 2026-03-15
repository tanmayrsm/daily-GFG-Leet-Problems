import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class OrderedPrinter {
    private int current = 1;
    private final int max;
    private int turn = 0; // which worker's turn: 0,1,2

    OrderedPrinter(int max) {
        this.max = max;
    }

    public synchronized void print(int myTurn, String name) {
        while (current <= max) {
            while (current <= max && turn != myTurn) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            if (current > max) {
                notifyAll();
                return;
            }
            System.out.println(current + " :: " + name);
            current++;
            turn = (turn + 1) % 3;
            notifyAll();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        int max = 100;
        OrderedPrinter printer = new OrderedPrinter(max);

        ExecutorService exec = Executors.newFixedThreadPool(3);

        exec.submit(() -> printer.print(0, "W0"));
        exec.submit(() -> printer.print(1, "W1"));
        exec.submit(() -> printer.print(2, "W2"));

        exec.shutdown();
    }
}
