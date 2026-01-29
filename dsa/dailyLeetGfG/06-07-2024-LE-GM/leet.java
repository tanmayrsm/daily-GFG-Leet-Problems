class Solution { // reff
    public int passThePillow(int n, int time) {
        int div = time / (n - 1);
        int rem = time % (n - 1);

        return div % 2 == 0 ? rem + 1 : n - rem;
    }
}

// 1 2 3 4
// t = 5