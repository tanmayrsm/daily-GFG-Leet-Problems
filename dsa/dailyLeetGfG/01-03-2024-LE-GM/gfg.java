
/*Complete the function below*/

class Solution
{
	// Function to find the peak element
	// arr[]: input array
	// n: size of array a[]
	public int peakElement(int[] arr,int n)
    {
       //add code here.
       int l = 0, r = n - 1, peak = -1;
       while(l <= r) {
           int mid = l + (r - l) / 2;
        //   System.out.println(l + "::" + mid + "::" + r);
           if((mid == 0 || arr[mid] > arr[mid - 1]) && (mid == n - 1 || arr[mid] > arr[mid + 1])) {
               return mid;
           }
           if(mid != n - 1 && arr[mid] <= arr[mid + 1]) {
               peak = mid+ 1;
               l = mid + 1;
           } else {
               r = mid - 1;
           }
       }
       return peak;
    }
}