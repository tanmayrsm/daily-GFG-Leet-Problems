
class GfG
{
    // this code will break in case of duplicate node data
    // eg - in[] = [1,1,1,1]
    //      post[] = [1,1,1,1]
    
    private static boolean[] vis;
    //Function to return a tree created from postorder and inoreder traversals.
    Node buildTree(int in[], int post[], int n) {
        // Your code here
        vis = new boolean[n];
        return solve(in, post, 0, n - 1);
    }
    
    Node solve(int in[], int post[], int l1, int r1) {
        if(l1 > r1)    return null;
        Node newer = null;
        int n = post.length;
        for(int i = n - 1;  i >= 0; i--) { // post order has root at right
            if(!vis[i]) {
                for(int j = l1; j <= r1; j++) {
                    if(in[j] == post[i]) {
                        newer = new Node(post[i]);
                        vis[i] = true;
                        newer.left = solve(in, post, l1, j - 1);
                        newer.right = solve(in, post, j + 1, r1);
                        return newer;
                    }
                }
            }
        }
        return null;
    }
}


// n = 8
// in[] = {4, 8, 2, 5, 1, 6, 3, 7}
// post[] = {8, 4, 5, 2, 6, 7, 3, 1}
// Output: 
// 1 2 4 8 5 3 6 7

// inorder - lvr
// postorder - lrv

// postordr me rightmost -> root