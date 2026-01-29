class Solution {
    public int[] minDifference(int n, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int[] ans = new int[k];
        int N = n, m = 0, K = 0;
        for (int i = 2;  i < N && n > 1; i++) {
            while (n > 1 && n % i == 0) {
                pq.add(i);
                m++;
                n /= i;
            }
        }
        if (k > m) {
            int rem = k - m;
            while(rem-- > 0) {
                ans[K++] = 1;
            }
            put(pq, ans, K);
        } else if (k == m) {
            put(pq, ans, K);
        } else {
            int rem = m - k;
            while(!pq.isEmpty() && rem > 0) {
                pq.offer(pq.poll() * pq.poll());
                rem--;
            }
            put(pq, ans, K);
        }
        return ans;
    }
    private void put(PriorityQueue<Integer> primeFactors, int[] ans, int K) {
        for (int i = K; i < ans.length; i++)
            ans[i] = primeFactors.poll();
    }
}

// 50 -> 2, 5, 5
// m = 3
// if k > m -> add 1s to lhs, or return last no - 1
// if k == m, return last - first
// else m - k nos can be removed
//     so remove from LHS only for max diff to be minimized, use a pq for this

// 44 -> 2, 2, 11

// 4, 11, 11
// 11, 44

// 100 -> 2 2 5 5
2 2 50 100 100
50 200 200 -> 150
100 100 200 -> 100

2 2 3 60 60






1. for no n, find prime factors and store them in PQ
2. if k > m, return last no - 1
        3. if k == m return last - first
4. else k < m, delete m - k nos, and do pq operation for them, and push them back in queue, and return last - first


2 2 3 3 5, k = 3
4 3 3 5
4 5 9 -> 5

5 6 6 -> 1


