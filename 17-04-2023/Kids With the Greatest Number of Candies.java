class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = Arrays.stream(candies).max().getAsInt();
        List<Boolean> ans = new ArrayList<>();
        for(int x : candies) {
            if(x + extraCandies >= max)
                ans.add(true);
            else    ans.add(false);
        }
        return ans;
    }
}