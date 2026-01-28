class Solution {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        int n = players.length, m = trainers.length, r = 0, ans = 0;
        for (int i = 0; i < n; i++) {
            while(r < m && trainers[r] < players[i])    r++;
            if (r == m) break;
            if (players[i] <= trainers[r])  ans++;
            r++;
        }
        return ans;
    }
}