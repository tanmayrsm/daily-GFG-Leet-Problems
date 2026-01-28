
class Solution{
    //Function to check whether there is a subarray present with 0-sum or not.
    static boolean findsum(int arr[],int n)
    {
        //Your code here
        Set<Integer> st = new HashSet<>();
        for(int i = 0; i < n; i++) {
            if(i > 0)
                arr[i] += arr[i - 1];
            if(arr[i] == 0) return true;
            if(st.contains(arr[i]))
                return true;
            else
                st.add(arr[i]);
        }
        return false;
        
    }
}