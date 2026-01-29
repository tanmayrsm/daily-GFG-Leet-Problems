class Solution {
    public boolean canBeEqual(int[] target, int[] arr) {
        int n = target.length;
        if (arr.length != n) return false;
        Arrays.sort(target);
        Arrays.sort(arr);
        for (int i = 0; i < n; i++)
            if (arr[i]!= tagrget[i])
                return false;
        return true;
    }
}
