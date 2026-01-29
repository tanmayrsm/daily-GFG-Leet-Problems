
class Solution {
    public static int pairsum(int[] arr) {
        // code here
        int l1 = 0, l2 = 0;
        for(int a : arr) {
            if(a > l1) {
                l2 = l1;
                l1 = a;
            } else if (a > l2)
                l2 = a;
        }
        return l1 + l2;
    }
}