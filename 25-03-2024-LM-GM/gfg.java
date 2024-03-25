//User function Template for Java

class Solution {
    private static ArrayList<String> ans;
    ArrayList<String> NBitBinary(int N) {
        // code here
        char[] arr = new char[N];
        ans = new ArrayList<>();
        solve(arr, 0, 0);
        return ans;
    }
    private static void solve(char[] arr, int ptr, int oneCt) {
        if(ptr == arr.length) {
            ans.add(new String(arr));
            return;
        }
        arr[ptr] = '1';
        solve(arr, ptr + 1, oneCt + 1);
        
        int noOfZeros = ptr - oneCt;
        if(noOfZeros + 1 <= oneCt) {
            arr[ptr] = '0';
            solve(arr, ptr + 1, oneCt);
        }
    }
}