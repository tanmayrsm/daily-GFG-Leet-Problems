class Solution {
    public boolean checkDuplicatesWithinK(int[] arr, int k) {
        // your code
        int l = 0;
        Set<Integer> st = new HashSet<>();
        for(int i = 0; i < k; i++)
            if(st.contains(arr[i]))
                return true;
            else st.add(arr[i]);
        for(int i = k; i < arr.length; i++) {
            if(st.contains(arr[i])) return true;
            st.remove(arr[l++]);
            st.add(arr[i]);
        }
        return false;
    }
}