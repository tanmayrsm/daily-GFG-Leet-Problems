class Solution {
  // reff -> https://www.youtube.com/watch?v=xlgOaIK-inc

  private int ans;
  public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
      ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
      ans = 0;
      for (int i = 0; i < n; i++) {
          adj.add(new ArrayList<>());
      }
      for (int[] edge : edges) {
          adj.get(edge[0]).add(edge[1]);
          adj.get(edge[1]).add(edge[0]);
      }

      dfs(0, -1, adj, values, k); // 0=node, -1=parent
      return ans; 
  }

  long dfs(int node, int parent, ArrayList<ArrayList<Integer>> adj, int[] values, int k) {
      long sum = values[node];

      for (Integer neighbour : adj.get(node)) {
          if (neighbour != parent) {
              sum += dfs(neighbour, node, adj, values, k);
          }
      }
      if (sum % k == 0) {
          ans++; // Update ans using the array
          return 0;
      }
      return sum;
  }
}