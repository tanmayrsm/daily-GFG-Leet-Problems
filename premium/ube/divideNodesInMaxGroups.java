class Solution {
    public int magnificentSets(int n, int[][] edges) {
        // check if graph is bipartite
        //      why ? to divide ur graph in two parts, where each nodes can be connected
        //      there has to be something, which can help me validate, as cyclic nodes, with 4 nodes qualify, but 3 cyclic nodes dont
        //      bipartite helps to know
        //        ->  In a bipartite graph, the set of vertices can be divided into two disjoint sets, such that no two vertices within the same set are adjacent.

        // how to find then max groups ?
        // if u look at examples other way, u just need a node from where u can start, and depth of tree is max (using BFS)

    }
}