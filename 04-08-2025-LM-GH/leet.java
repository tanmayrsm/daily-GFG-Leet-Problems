class Solution {
    public int totalFruit(int[] fruits) {
        int n = fruits.length, l = 0, ans = 0;
        int[] type = new int[2];
        int[] ct = new int[2];
        Arrays.fill(type, -1);
        for (int i = 0; i < n; i++) {
            if (type[0] == -1) {
                type[0] = fruits[i];
                ct[0]++;
            } else if (type[1] == -1 && type[0] != fruits[i]) {
                type[1] = fruits[i];
                ct[1]++;
            } else if (type[0] == fruits[i] || type[1] ==fruits[i]) {
                ct[(type[0] == fruits[i] ? 0 : 1)]++;
            } else {
                while (l < i) {
                    int whoWillDecrease = (type[0] == fruits[l] ? 0 : 1);
                    ct[whoWillDecrease]--;
                    l++;
                    if (ct[whoWillDecrease] == 0) {
                        type[whoWillDecrease] = fruits[i];
                        ct[whoWillDecrease] = 1;
                        break;
                    }
                }
            }
            ans = Math.max(ans, ct[0] + ct[1]);
        }
        return ans;
    }
}