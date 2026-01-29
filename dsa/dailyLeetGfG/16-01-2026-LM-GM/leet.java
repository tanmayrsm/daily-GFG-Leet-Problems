class Solution {
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        int N = hFences.length + 2, M = vFences.length + 2;

        Arrays.sort(hFences);
        Arrays.sort(vFences);

        Set<Integer> hLen = new HashSet<>();
        Set<Integer> vLen = new HashSet<>();
        long ans = -1;

        // needed  HFence, vFences with boundaries, hence using a new array
        List<Integer> hL = new ArrayList<>();
        hL.add(1);
        List<Integer> vL = new ArrayList<>();
        vL.add(1);
        for(int x : hFences)    hL.add(x);
        for(int x : vFences)    vL.add(x);
        hL.add(m);
        vL.add(n);

        for(int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++)
                hLen.add(hL.get(j) - hL.get(i));
        }

        for(int i = 0; i < M; i++) {
            for (int j = i + 1; j < M; j++) {
                int diff = vL.get(j) - vL.get(i);
                if(hLen.contains(diff)) {
                    ans = Math.max(ans, diff);
                }
            }
        }
        if(ans == -1)   return -1;
        return (int)((ans * ans) % 1000000007);


    }
}