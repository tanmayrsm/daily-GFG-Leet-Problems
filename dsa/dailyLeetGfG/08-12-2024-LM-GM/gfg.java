class Solution {
    public List<int[]> mergeOverlap(int[][] arr) {
        // Code here // Code here
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> ans = new ArrayList<>();
        ans.add(new int[] {arr[0][0], arr[0][1]});
        int lastIdx = 0;
        for(int i = 1; i < arr.length; i++) {
            if(ans.get(lastIdx)[1] >= arr[i][0] && ans.get(lastIdx)[1] < arr[i][1]) {
                ans.get(lastIdx)[1] = arr[i][1];
            } else if(ans.get(lastIdx)[1] < arr[i][0]) {
                ans.add(new int[] {arr[i][0], arr[i][1]});
                lastIdx++;
            }
        }
        
        return ans;
    }
}