class Solution {
    public int minSplitMerge(int[] nums1, int[] nums2) {
        int n = nums1.length, steps = 0;
        Queue<Integer[]> q = new LinkedList<>();
        Integer[] n1 = new Integer[n];
        Integer[] n2 = new Integer[n];

        for (int i = 0; i < n; i++) {
            n1[i] = nums1[i];
            n2[i] = nums2[i];
        }

        Set<String> st = new HashSet<>();
        q.offer(n1);
        while (!q.isEmpty()) {
            Integer[] res = q.poll();
            if(isEqual(res, n2)) return steps;
            String hash = getHash(res);
            if (st.contains(hash))   continue;
            else st.add(hash);
            System.out.println("------");
            print(res);
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    solve(i, j, res, n, q);
                }
            }
            steps++;
        }
        return -1;
    }
    private boolean isEqual(Integer[] x, Integer[] y) {
        for (int i = 0; i < x.length; i++)
            if (x[i] != y[i])   return false;
        return true;
    }
    private void solve(int i, int j, Integer[] nums, int n, Queue<Integer[]> q) {
        Integer[] resR = new Integer[n];
        int size = j - i + 1;
        int I = i;
        // System.out.println("ss ::" + i + "::" + j + "::" + size + "::" + rR);
        // from between i - j
        for (int k = 0; k < n; k++) {
            Integer[] resL = new Integer[n];
            for (int y = k; y < k + size && y < n && I < n; y++) {
                resL[y] = nums[I];
                nums[I++] = null;
            }
            I = 0;
            for (int y = 0; y < n; y++) {
                if (resL[y] != null) {
                    while (I < n && nums[I] == null) I++;
                    if (I < n)
                        resL[y] = nums[I];
                }
            }
            q.offer(resL);
        }
    }
    private void print(Integer[] nums) {
        for (int i = 0; i < nums.length; i++)
            System.out.print(nums[i] + "::");
        System.out.println();
    }
    private String getHash(Integer[] nums) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++)
            sb.append(nums[i] + "::");
        return sb.toString();
    }
}