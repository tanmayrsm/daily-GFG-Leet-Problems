
class Solution {
    private static int max = Integer.MAX_VALUE;
    int findCity(int n, int m, int[][] edges, int distanceThreshold) {

      // Initialize the distance matrix with maximum values
      int[][] distance = new int[n][n];
      for (int i = 0; i < n; i++) {
          Arrays.fill(distance[i], Integer.MAX_VALUE / 2); // Dividing by 2 to avoid overflow
          distance[i][i] = 0; // Distance from a city to itself is 0
      }
      
      // Update distances based on given edges
      for (int[] edge : edges) {
          int from = edge[0];
          int to = edge[1];
          int weight = edge[2];
          distance[from][to] = weight;
          distance[to][from] = weight; // Since edges are bidirectional
      }
      
      // Apply Floyd-Warshall algorithm to find shortest paths
      for (int k = 0; k < n; k++) {
          for (int i = 0; i < n; i++) {
              for (int j = 0; j < n; j++) {
                  if (distance[i][k] + distance[k][j] < distance[i][j]) {
                      distance[i][j] = distance[i][k] + distance[k][j];
                  }
              }
          }
      }
      
      int minCities = Integer.MAX_VALUE;
      int resultCity = -1;
      
      // Check for each city
      for (int i = 0; i < n; i++) {
          int reachableCities = 0;
          for (int j = 0; j < n; j++) {
              if (distance[i][j] <= distanceThreshold) {
                  reachableCities++;
              }
          }
          // Update the result if the current city has fewer reachable cities
          // or the same number of reachable cities but a greater label
          if (reachableCities < minCities || (reachableCities == minCities && i > resultCity)) {
              minCities = reachableCities;
              resultCity = i;
          }
      }
      
      return resultCity;
  }
}
