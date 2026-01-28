
class Solution {
    static int isPossible(int N, int arr[]) {
        // code here
        long sum = 0;
        for(int x : arr)
            sum += x;
        return sum % 3 == 0 ? 1 : 0;
    }
}