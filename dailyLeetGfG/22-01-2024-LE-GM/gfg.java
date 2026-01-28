
class Solution
{
    private static ArrayList<ArrayList<Integer>> ans;
    public static ArrayList<ArrayList<Integer>> printPaths(Node root, int sum)
    {
        // code here
        ans = new ArrayList<>();
        if(root == null)    return ans;
        
        dfs(root, new ArrayList<>(Arrays.asList(root.data)), root.data, sum, 1);
        return ans;
    }
    private static void dfs(Node root, ArrayList<Integer> currList, int currSum, int target, int currSize) {
        // CALL BY VALUE vs CALL BY REF -
        // remember , if u do
        // dfs(..., Integer currSum, Integer target, Integer currSize) {
        // u wont get appropriate results, if u call this function again as
        // dfs(..., currSum, target, currSize + 1);
        // as ur updated value will never be passed in next call, only reference to this Integer class variable


        if(root == null)    return ;
        if(currSum == target) {
            ans.add(new ArrayList<>(currList));
        }

        if(root.left != null) {
            int data = root.left.data;
            currSum += data;
            currList.add(data);
            dfs(root.left, currList, currSum, target, currSize + 1);
            currSum -= data;
            currList.remove(currSize);
        }
        if(root.right != null) {
            int data = root.right.data;
            currSum += data;
            currList.add(data);
            dfs(root.right, currList, currSum, target, currSize + 1);
            currSum -= data;
            currList.remove(currSize);
        }

    }
}