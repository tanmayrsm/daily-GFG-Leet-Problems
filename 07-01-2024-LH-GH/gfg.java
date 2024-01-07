
class Solution {
    // INTUITION HAS BEEN ADDED
    // for more details - https://www.youtube.com/watch?v=Z0hwjftStI4
    
    static int splitArray(int[] arr , int N, int K) {
        // code here
        Integer minimum = Arrays.stream(arr).boxed().max((i1, i2) -> Integer.compare(i1, i2)).get();  
        // best case = k paritions, where k == n,
        // hence minimum split will be max Of whole array
        
        int maxSum = Arrays.stream(arr).sum();          
        // k == 1, worst case, one parition only, sum of array is min possible
        

        // linear search - gives TLE
        // for(int i = minimum; i <= maxSum; i++) {
        //     if(canMakePartitions(i, arr, K, N))
        //         return i;
        // }
        
        // binary search
        int l = minimum, r = maxSum;
        while(l <= r) {
            int mid = (l + r) / 2;
            if(canMakePartitions(mid, arr, K, N))
                r = mid - 1;
            else l = mid + 1;
        }
        return l;
    }
    
    private static boolean canMakePartitions(int canHoldSum, int[] arr, int k, int n) {
        int currSum = arr[0];
        int noOfPartitions = 1;
        for(int i = 1; i < arr.length; i++) {
            if(currSum + arr[i] > canHoldSum) {
                noOfPartitions++;
                currSum = arr[i];
                if(noOfPartitions > k) {    // need not more than k partitions
                    return false;
                }
            } 
            else if(k - noOfPartitions >= n - i) { // though this item can hold more sum, 
                                                    // but remaining items must be enough to 
                                                    // distribute among remaining 'k' instances,
                                                    // hence making a partition here
                noOfPartitions++;
                currSum = arr[i];
            } 
            else currSum += arr[i];
        } 
        return noOfPartitions == k;
    }
};