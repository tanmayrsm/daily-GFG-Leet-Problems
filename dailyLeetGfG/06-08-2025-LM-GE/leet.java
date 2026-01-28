class SegmentTree {
    int[] max;

    public SegmentTree(int[] arr) {
        int n = arr.length;
        this.max = new int[4 * n];
        build(1, 0, n - 1, arr);
    }

    private void build(int node, int left, int right, int[] arr) {
        // base case
        if(left == right) {
            max[node] = arr[left];
            return;
        }

        int mid = left + (right - left) / 2;
        build(2 * node, left, mid, arr);
        build(2 * node + 1, mid + 1, right, arr);
        update(node);
    }

    private void update(int node) {
        max[node] = Math.max(max[node * 2], max[node * 2 + 1]);
    }

    public boolean query(int node, int left, int right, int target) {
        // no basket available
        if(max[node] < target) return false;
        // base case
        if(left == right) {
            // found a basket
            max[node] = -1;
            return true;
        }

        int mid = left + (right - left) / 2;
        boolean result = query(node * 2, left, mid, target);
        if(!result) result = query(node * 2 + 1, mid + 1, right, target);

        update(node);
        return result;
    }
}

class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        SegmentTree tree = new SegmentTree(baskets);
        int result = 0;
        for(int fruit: fruits) {
            if(!tree.query(1, 0, baskets.length - 1, fruit)) result++;
        }
        return result;
    }
}