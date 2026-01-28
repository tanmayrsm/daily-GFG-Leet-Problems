
class Solution{ // referred soln
    private int[] parent;
    private int[] size;
    private int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int largestIsland(int N,int[][] grid) 
    {
        parent = new int[N * N];
        size = new int[N * N];
        
        // Initialize parent and size arrays
        for (int i = 0; i < N * N; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        
        // Step 1: Union islands
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1) {
                    for (int[] dir : directions) {
                        int ni = i + dir[0];
                        int nj = j + dir[1];
                        
                        if (ni >= 0 && ni < N && nj >= 0 && nj < N && grid[ni][nj] == 1) {
                            union(i * N + j, ni * N + nj);
                        }
                    }
                }
            }
        }
        
        // Step 2: Count sizes of connected components
        int maxIslandSize = 0;
        int[] componentSizes = new int[N * N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 0) {
                    int componentSize = 1;
                    Set<Integer> connectedComponents = new HashSet<>();
                    for (int[] dir : directions) {
                        int ni = i + dir[0];
                        int nj = j + dir[1];
                        
                        if (ni >= 0 && ni < N && nj >= 0 && nj < N && grid[ni][nj] == 1) {
                            int parent = find(ni * N + nj);
                            if (!connectedComponents.contains(parent)) {
                                componentSize += size[parent];
                                connectedComponents.add(parent);
                            }
                        }
                    }
                    maxIslandSize = Math.max(maxIslandSize, componentSize);
                }
            }
        }
        
        return maxIslandSize == 0 ? N * N : maxIslandSize;
    }
    
    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    
    private void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        
        if (rootX != rootY) {
            if (size[rootX] < size[rootY]) {
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
            } else {
                parent[rootY] = rootX;
                size[rootX] += size[rootY];
            }
        }
    }
}
