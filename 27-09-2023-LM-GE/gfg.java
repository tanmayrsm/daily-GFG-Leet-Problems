import java.util.ArrayList;
import java.util.Arrays;

//User function Template for Java

class Solution{
    // Function for finding maximum and value pair
    public static ArrayList<Integer> printClosest (int arr[], int brr[], int n, int m, int x) {
        // code here
        int a = -1, b = -1;
        int closest = Integer.MAX_VALUE;
        int l = 0, r = m - 1;
        while(l < n && r >= 0) {
            int no = arr[l] + brr[r];
            if(Math.abs(x - no) < closest) {
                a = arr[l]; b = brr[r];
                closest = Math.abs(x - no);
            }
            if(no > x)  r--;
            else l++;
        }
        return new ArrayList<>(Arrays.asList(a, b));
    }
}