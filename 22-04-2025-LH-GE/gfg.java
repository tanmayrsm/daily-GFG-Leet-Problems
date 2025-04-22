
class Solution {
    public int findUnique(int[] arr) {
        // code here
        int a = 0;

        for (int i : arr)
         a ^= i;
         
        return a;
    }
}