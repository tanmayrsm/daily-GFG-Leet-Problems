class Solution {
    public int maximalNetworkRank(int n, int[][] roads) {
        // one of node will be node with maximum  indegree
        // second node can be one which is connected to it, or one not connected to it
        int firstNodeIndegree = 0;
        int node1 = -1;
        int[] indegree = new int[n];
        Map<Integer, Set<Integer>> adjList = new HashMap<>();
        List<Integer> sameIndegree = new ArrayList<>();

        // construct adj list and indegree array
        for(int[] road : roads) {
            int x = road[0], y = road[1];
            indegree[x]++;
            indegree[y]++;

            if(adjList.get(x) == null)
                adjList.put(x, new HashSet<>());
            if(adjList.get(y) == null)
                adjList.put(y, new HashSet<>());
            adjList.get(x).add(y);
            adjList.get(y).add(x);

            if(indegree[x] >= firstNodeIndegree && indegree[x] >= indegree[y])
                node1 = x;
            if (indegree[y] > firstNodeIndegree && indegree[y] > indegree[x])
                node1 = y;

            firstNodeIndegree = Math.max(firstNodeIndegree, Math.max(indegree[x], indegree[y]));
        }

        // incase of similar max indegree, add them in list
        for(int i = 0; i < n; i++) {
            if(indegree[i] == firstNodeIndegree)    sameIndegree.add(i);
        }
        // and try to find, if no connection between any two nodes
        for(int i = 0; i < sameIndegree.size(); i++) {
            int n1 = sameIndegree.get(i);
            for(int j = i + 1; j < sameIndegree.size(); j++) {
                int n2 = sameIndegree.get(j);
                if(adjList.get(n1) != null && !adjList.get(n1).contains(n2)) {
                    return firstNodeIndegree * 2;
                }
            }
        }
        
        // check for second node, which will have less or equal indegree
        int secondNodeIndegree = 0;
        for(int i = 0; i < n; i++) {
            if(i != node1) {
                if(adjList.get(node1) != null && adjList.get(node1).contains(i)) {
                    secondNodeIndegree = Math.max(secondNodeIndegree, indegree[i] - 1);
                } else  secondNodeIndegree = Math.max(secondNodeIndegree, indegree[i]);
            }
        }

        return firstNodeIndegree + secondNodeIndegree;
    }
}