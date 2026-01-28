class Solution {
    public int celebrity(int mat[][]) {
        // code here
        int n = mat.length;
        Map<Integer, Set<Integer>> chief = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (!chief.containsKey(i))  chief.add(i, new HashSet<>());
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1 && i != j) {
                    chief.remove(i);
                    chief.get(j).add(i);
                }
            }
        }
        for(Map.Entry<Integer, Set<Integer>> mp : chief.entrySet()) {
            if(mp.getValue().size() == n-1) return mp.getKey();
        }
        return -1;
    }
}

[1, 1, 0],
[0, 1, 0],
[0, 1, 1]

0 -> 1
2 -> 1


