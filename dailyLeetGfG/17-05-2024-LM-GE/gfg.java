
class Solution {
    public int findPair(int n, int x, int[] arr) {
        // code here
        Arrays.sort(arr);
        Set<Integer> st = new HashSet<>();
        for(int num : arr) {
            int req = (num > x) ? num - x : x - num;
            if(st.contains(req) && Math.abs(num - req) == x)  return 1;
            st.add(num);
        }
        return -1;
    }
}