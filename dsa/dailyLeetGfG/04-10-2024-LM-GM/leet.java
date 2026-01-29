class Solution {
    public long dividePlayers(int[] skill) {
        int n = skill.length, l = 0, r = n - 1;
        long ans = 0, reqAns = -1;
        Arrays.sort(skill);
        for(int i = 0; i < n / 2; i++) {
            if(reqAns == -1)
                reqAns = skill[l] + skill[r];
            else if(skill[l] + skill[r] != reqAns)    return -1;
            ans += skill[l] * skill[r];
            l++;
            r--;
        }
        return ans;
    }
}