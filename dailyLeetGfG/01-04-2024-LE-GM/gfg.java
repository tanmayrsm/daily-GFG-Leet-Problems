
/*

Definition for Binary Tree Node
class Node
{
    int data;
    Node left;
    Node right;

    Node(int data)
    {
        this.data = data;
        left = null;
        right = null;
    }
}
*/

class Solution {
    private static int[] list;
    private static int ct;
    // instance of count inversions problem
    public static int pairsViolatingBST(int n, Node root) {
        // code here
        list = new int[n];
        ct = 0;
        dfs(root);
        return mergeSortAndCount(list, 0, n - 1);
    }
    
    private static void dfs(Node root) {
        if(root == null)    return;
        dfs(root.left);
        list[ct++] = root.data;
        dfs(root.right);
    }
    
    // Merge sort function
    private static int mergeSortAndCount(int[] arr, int l,
                                         int r) {
        // Keeps track of the inversion count at a
        // particular node of the recursion tree
        int count = 0;
    
        if (l < r) {
            int m = (l + r) / 2;
    
            // Total inversion count = left subarray count
            // + right subarray count + merge count
    
            // Left subarray count
            count += mergeSortAndCount(arr, l, m);
    
            // Right subarray count
            count += mergeSortAndCount(arr, m + 1, r);
    
            // Merge count
            count += mergeAndCount(arr, l, m, r);
        }
    
        return count;
    }
    private static int mergeAndCount(int[] arr, int l,
                                         int m, int r) {
        // Left subarray
        int[] left = Arrays.copyOfRange(arr, l, m + 1);
    
        // Right subarray
        int[] right = Arrays.copyOfRange(arr, m + 1, r + 1);
    
        int i = 0, j = 0, k = l, swaps = 0;
    
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j])
                arr[k++] = left[i++];
            else {
                arr[k++] = right[j++];
                swaps += (m + 1) - (l + i);
            }
        }
        while (i < left.length)
            arr[k++] = left[i++];
        while (j < right.length)
            arr[k++] = right[j++];
        return swaps;
    }
        
    // 50 10 20 40 30
}
