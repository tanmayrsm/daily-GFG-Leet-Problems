
//User function Template for Java

class Solution
{
    public void swapElements(int[] arr, int n)
    {
        // Code here
        for(int i = 0; i < n - 2; i++) {
            int temp = arr[i + 2];
            arr[i + 2] = arr[i];
            arr[i] = temp;
        }
    }
}