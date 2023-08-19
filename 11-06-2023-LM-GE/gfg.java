
class Solution {

    public static void update(int a[], int n, int updates[], int k)
    {
        // Your code goes here
        for(int x : updates) {
            a[x-1]++;
        }
        for(int i = 1; i < n; i++) {
            a[i] += a[i-1];
        }
    }
    
}
