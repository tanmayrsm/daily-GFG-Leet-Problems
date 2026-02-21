class Solution {
    public ArrayList<Integer> missingRange(int[] arr, int low, int high) {
        // code here
        int maxo = Arrays.stream(arr).max().getAsInt();
        ArrayList<Integer> ans = new ArrayList<Integer>();
        maxo = Math.max(maxo, high);

        int[] nums = new int[maxo];
        for(int x : arr)
            nums[x - 1]++;
        low--;
        high--;
        for(int i = 0; i < nums.length; i++) {
            if(i >= low && i <= high && nums[i] == 0)
                ans.add(i + 1);
        }
        return ans;
    }
}