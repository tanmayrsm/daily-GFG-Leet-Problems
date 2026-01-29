class Solution {
    // Function to find equilibrium point in the array.
    public static int findEquilibrium(int arr[]) {
        // code here
        long sum = 0;
        for (int x : arr)
            sum += x;
        long currSum = 0;
        for(int i = 0; i < arr.length; i++) {
            sum -= arr[i];
            if (currSum == sum)
                return i;
            currSum += arr[i];
            
        }
        return -1;
    }
}