
class Solution
{
    // arr[]: input array
    // n: size of the array
    //Function to find triplets with zero sum.
	public boolean findTriplets(int arr[] , int n)
    {
        //add code here.
        Arrays.sort(arr);
        for(int i = 0; i < n; i++) {
            boolean got = findSum(arr, i + 1, arr[i] * -1);
            if(got) return true;
        }
        return false;
    }
    
    private static boolean findSum(int[] arr, int l, int target) {
        int r = arr.length - 1;
        while(l < r) {
            if(arr[l] + arr[r] == target)   return true;
            else if (arr[l] + arr[r] > target)  r--;
            else    l++;
        }
        return false;
    }
}