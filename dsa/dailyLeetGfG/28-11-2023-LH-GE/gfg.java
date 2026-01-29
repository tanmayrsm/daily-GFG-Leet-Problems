
class Solution {
    int sumOfDependencies(ArrayList<ArrayList<Integer>> adj, int V) {
        // code here
        return adj.stream().map(item -> item.size()).mapToInt(i -> i).sum();
    }
};