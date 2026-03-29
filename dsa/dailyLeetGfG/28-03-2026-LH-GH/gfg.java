class Solution {
    static int timer;
    public static void tarjanAlgo(ArrayList<ArrayList<Integer>> graph, boolean[] isVisited, int curr, int parent, int[] dt, int[] ldt, boolean[] arti){
        isVisited[curr] = true;
        dt[curr] = ldt[curr] = ++timer; // Set discovery time and low-link value

        int child = 0; // Count of child nodes in DFS tree
        for(int neighbour : graph.get(curr)){
            if(neighbour==parent){
                // Ignore the edge going back to the parent
                continue;
            }else if(!isVisited[neighbour]){
                // If neighbor is not visited, do DFS recursively
                tarjanAlgo(graph, isVisited, neighbour, curr, dt, ldt, arti);

                // Low-link update karo: matlab subtree se sabse chota discovery time
                ldt[curr] = Math.min(ldt[curr], ldt[neighbour]);

                // Agar ye condition true hai to current node articulation point hai (root ke liye nahi)
                if(ldt[neighbour]>=dt[curr] && parent!=-1){
                    arti[curr] = true;
                }
                child++; // incremnet child count
            }else{
                // Agar neighbor visit ho chuka hai, to low-link ko update karo
                ldt[curr] = Math.min(ldt[curr], dt[neighbour]);
            }
        }
        // Special case: agar root node hai aur uske 1 se zyada child hain, to wo bhi articulation point
        if(parent==-1 && child>1){
            arti[curr] = true;
        }
    }
    static ArrayList<Integer> articulationPoints(int V, int[][] edges) {

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i=0; i<V; i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<edges.length; i++){
            int u = edges[i][0];
            int v = edges[i][1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }


        ArrayList<Integer> result = new ArrayList<>();
        boolean[] arti = new boolean[V]; // tom mark the articulation points
        boolean[] isVisited = new boolean[V];
        int[] dt = new int[V]; // discovery time
        int[] ldt = new int[V]; // lowest dicovery time
        timer = 0;

        for(int i=0; i<V; i++){
            if(!isVisited[i]){
                tarjanAlgo(graph, isVisited, i, -1, dt, ldt, arti);
            }
        }

        // Jo node articulation point hai, result mein add karo
        for(int i = 0; i<V; i++){
            if(arti[i]){
                result.add(i);
            }
        }
        // Agar koi articulation point nahi mila to -1 return karo
        if(result.isEmpty()){
            result.add(-1);
        }

        return result;
    }
}