class Main {

    static class Printer {
        private int current = 1;
        private final int max;
        private int turn = 0; // 0,1,2 → which thread's turn

        Printer(int max) {
            this.max = max;
        }

        public synchronized void print(int myTurn, String name) {
            while (current <= max) {
                // not my turn → wait
                while (current <= max && turn != myTurn) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                if (current > max) {
                    notifyAll(); // wake others to exit
                    return;
                }
                System.out.println(current + " :: " + name);
                current++;

                // pass turn to next thread
                turn = (turn + 1) % 3;
                notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        int max = 100;
        Printer printer = new Printer(max);

        Runnable r0 = () -> printer.print(0, "T0");
        Runnable r1 = () -> printer.print(1, "T1");
        Runnable r2 = () -> printer.print(2, "T2");

        new Thread(r0).start();
        new Thread(r1).start();
        new Thread(r2).start();
    }
}
