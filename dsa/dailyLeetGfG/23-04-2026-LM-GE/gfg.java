class Solution {
    public boolean canSplit(int arr[]) {
        // code here
        // code here
        int sum = 0;
        for(int i : arr) {
            sum += i;
        }
        if(sum % 2 == 1) return false;

        int halfSum = 0;
        for(int i: arr) {
            halfSum += i;
            if(halfSum == sum/2) {
                return true;
            }
        }

        return false;
    }
}