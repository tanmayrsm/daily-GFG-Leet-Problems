class Solution {
    
    private static boolean isLeftDone;
    private static ArrayList<Integer> arr;
	private static ArrayList<Integer> rightSubtree;
    
	ArrayList <Integer> boundaryTraversal(Node node) {
		arr = new ArrayList<>();
		isLeftDone = false;
		rightSubtree = new ArrayList<>();

		dfs(node, true);
		dfsForRight(node.right, true);
		if(rightSubtree.size() > 0) {
		    for(int i = rightSubtree.size() - 1; i >= 0; i--)
    		    arr.add(rightSubtree.get(i));
		}
		return arr;
	}
    private static void dfs(Node node, boolean isRoot) {
		if(node == null)
			return;
		if (node.left == null && node.right == null) {
			isLeftDone = true;
			arr.add(node.data);
			return;
		}
		
		else if(!isLeftDone)
			arr.add(node.data);

		dfs(node.left, false);
		if(!isRoot)
			dfs(node.right, false);
		
	}
	private static void dfsForRight(Node node, boolean isNodeRightMost) {
		if(node == null)
			return;
		if(node.left == null && node.right == null && !isNodeRightMost) {
			arr.add(node.data);
			return;
		}
		if(isNodeRightMost) {
			rightSubtree.add(node.data);
		    
		}

		if(node.right != null) {
			dfsForRight(node.left, false);
		} else if(node.right == null) {
			dfsForRight(node.left, isNodeRightMost);
		}
		dfsForRight(node.right, isNodeRightMost);
	}
}