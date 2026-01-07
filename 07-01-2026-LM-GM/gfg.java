class Solution {
    ArrayList<Integer> countDistinct(int arr[], int k) {
        // code here
        Map<Integer, Integer> st = new HashMap<>();
        int n = arr.length;
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0; i < k; i++)  {
            st.put(arr[i], st.getOrDefault(arr[i], 0) + 1);
        }
        int currSize = st.size();
        ans.add(currSize);

        for (int i = k; i < n; i++) {
            if(!st.containsKey(arr[i])) {
                st.put(arr[i], 1);
                currSize++;
            } else st.put(arr[i], st.get(arr[i]) + 1);

            if(st.get(arr[i - k]) == 1) {
                st.remove(arr[i - k]);
                currSize--;
            } else st.put(arr[i - k], st.get(arr[i - k]) - 1);
            ans.add(currSize);
        }
        return ans;
    }
}