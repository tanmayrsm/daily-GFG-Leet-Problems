class Solution {
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        int[] indegree = new int[n];
        List<Integer> ans = new ArrayList<>();
        for(List<Integer> x : edges) {
            indegree[x.get(1)]++;
        }
        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 0)
                ans.add(i);
        }

        return ans;
    }
}