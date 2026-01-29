import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public boolean isPossible(int n, List<List<Integer>> edges) {
        Set<List<Integer>> edgeSet = new HashSet<>(edges);
        List<Integer> oddVertex = new ArrayList<>();
        int[] inorder = new int[n];
        for (List<Integer> edge : edges) {
            inorder[edge.get[0] - 1]++;
            inorder[edge.get[1] - 1]++;
        }

        for (int i = 0; i < n; i++) {
            if (inorder[i] % 2 == 1) {
                oddVertex.add(i);
            }
        }
        int m = oddVertex.size(), edgeAdded = 0;
        if (m > 4)   return false;

        for (int i = 0; i < m; i++) {
            boolean isEdgeAdded = false;
            for (int j = i + 1; j < m; j++) {
                List<Integer> expectedEdge = Arrays.asList(oddVertex.get(i), oddVertex.get(j));
                if (!edgeSet.contains(expectedEdge)) {
                    edgeSet.add(expectedEdge);
                    edgeAdded++;
                    isEdgeAdded = true;
                    break;
                }
            }
            if (!isEdgeAdded)   return false;
        }

        return true;
    }
}