class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int n = people.length;
        int ans = 0;
        int l = 0, r = n - 1;
        while(l <= r) {
            if(people[l] + people[r] <= limit) {
                ans++;
                l++;
                r--;
            } else {
                ans++;
                r--;
            }
        }
        return ans;
    }
}