class Solution {
    public long findSmallest(int[] arr) {
        // Your code goes here
        if(arr[0] > 1)  return 1;
        for(int i = 1; i < arr.length; i++) {
            if(arr[i] - arr[i - 1] > 1)
                return arr[i - 1] + 1;
        }
        return arr.length;
    }
}


// if first consecutive 'n' nos => n + 1
// else if first no > 1, return 1
// else go thru list, and find a gap => 1 2 3 5 6 => 4