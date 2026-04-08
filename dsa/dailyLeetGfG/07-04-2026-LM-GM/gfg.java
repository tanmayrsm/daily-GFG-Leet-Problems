class Solution {
    public int[] stableMarriage(int[][] men, int[][] women) {
        int n = men.length;

        // result[i] = woman matched with man i
        int[] result = new int[n];

        // womanPartner[j] = man currently engaged to woman j
        int[] womanPartner = new int[n];
        Arrays.fill(womanPartner, -1);

        // next woman index each man will propose to
        int[] next = new int[n];

        // ranking of men for each woman
        int[][] rank = new int[n][n];
        for (int w = 0; w < n; w++) {
            for (int i = 0; i < n; i++) {
                rank[w][women[w][i]] = i;
            }
        }

        // queue of free men
        Queue<Integer> freeMen = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            freeMen.add(i);
        }

        while (!freeMen.isEmpty()) {
            int man = freeMen.poll();

            // get next preferred woman
            int woman = men[man][next[man]];
            next[man]++;

            if (womanPartner[woman] == -1) {
                // woman is free
                womanPartner[woman] = man;
                result[man] = woman;
            }
            else {
                int currentMan = womanPartner[woman];

                // woman chooses better option
                if (rank[woman][man] < rank[woman][currentMan]) {
                    womanPartner[woman] = man;
                    result[man] = woman;

                    // previous partner becomes free
                    freeMen.add(currentMan);
                }
                else {
                    // proposal rejected
                    freeMen.add(man);
                }
            }
        }

        return result;
    }
}