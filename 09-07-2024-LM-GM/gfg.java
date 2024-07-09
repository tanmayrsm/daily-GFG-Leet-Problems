
// User function Template for Java

class Solution {
    static int threeSumClosest(int[] array, int target) {
        // code here
        Arrays.sort(array);
        int n = array.length;
        int closest = Integer.MAX_VALUE, no = -1;
        for(int i = 0; i < n - 2; i++) {
            int l = i + 1, r = n - 1;
            while(l < r) {
                int sum = array[i] + array[l] + array[r];
                if(Math.abs(target - sum) <= Math.abs(closest)) {
                    // System.out.println(closest + "::" + no + "::" + sum);
                    if (Math.abs(target - sum) == Math.abs(closest))
                        no = Math.max(no, sum);
                    else no = sum;
                    closest = target - sum;
                }
                if(sum < target) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return no;
    }
}
