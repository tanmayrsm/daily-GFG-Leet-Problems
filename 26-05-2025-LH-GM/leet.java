class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        List<List<Integer>> adjList = new ArrayList<>() ;
        int length = colors.length() ;
        int inDegree[] = new int[length] ;
        int colorsDP[][] = new int[length][26] ;
        int visited = 0 ;

        for(int i=0 ; i<length ; i++){
            adjList.add(new ArrayList<>());
        }

        for(int i=0 ; i<edges.length ; i++){
            int start = edges[i][0] ;
            int end = edges[i][1] ;
            adjList.get(start).add(end) ;
            inDegree[end]++ ;
        }

        // In DAG there will atleast one node whose indegree will be 0,
        // and such a nodes or nodes will our starting point
        Queue<Integer> queue = new LinkedList<>() ;
        for(int i=0 ; i<inDegree.length ; i++){
            if(inDegree[i] == 0){
                queue.add(i);
            }
        }

        while(!queue.isEmpty()){
            int parent = queue.poll() ;
            int parentColor = colors.charAt(parent) - 'a' ;
            //incrementing the parent's color in colorsDP
            colorsDP[parent][parentColor] = colorsDP[parent][parentColor]+1 ;

            //for all neighbours of the parent or current node we are processing
            for(Integer child : adjList.get(parent)){
                //reduce the indegree of that child
                inDegree[child]-- ;
                //indegree if zero than that child/node can behave as source now
                if(inDegree[child] == 0){
                    queue.add(child);
                }

                // Copying all the parent color into child but keeping the max color of child
                // Hence we can find MAX COLOR till this child.
                // Or we can say that we our keeping track of all colors till that particular child node/destination node where source is the parent here
                for(int i=0 ; i<26 ; i++){
                    colorsDP[child][i] = Math.max(colorsDP[child][i] , colorsDP[parent][i]);
                }
            }

            visited++ ; //this counter is done to maintain
        }

        //If we couldn't visit all the nodes than there is a cycle
        if(visited != length)
            return -1 ;


        int maxColor = 0 ;

        for(int i=0 ; i<colorsDP.length ; i++){
            for(int j=0 ; j<26 ; j++){
                maxColor = Math.max(maxColor , colorsDP[i][j]);
            }
        }

        return maxColor ;
    }


}