
/*The Node structure is defined as
struct Node {
    int data;
    Node *left;
    Node *right;

};
*/
class Solution
{
    private static int max = Integer.MAX_VALUE, previous;   
    int absolute_diff(Node root) {
        //Your code here
        // print inorder, list will be sorted
        // min diff could only exists between any two consecutive elements
        previous = max;
        return dfs(root);   
    }
    
    private static int dfs(Node root) {
        if(root == null)    return max;
        int ans = max;
        int l = dfs(root.left);
        if(previous != max) {
            ans = root.data - previous;
        }
        previous = root.data;
        int r = dfs(root.right);
        return Math.min(ans, Math.min(l, r));
    }
}


// 1 4 67 88 90
//  3 64 21 2


// 10 30 35 57 60 90
//  20  5  22 3  30 

//1 5 6 8 15
