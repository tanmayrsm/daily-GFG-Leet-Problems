
class Solution {
    ArrayList<Integer> removeDuplicate(int arr[]) {
        // code here
        Set<Integer> st = new LinkedHashSet<>();
        for(int a : arr)
            st.add(a);
        return new ArrayList<>(st);
    }
}