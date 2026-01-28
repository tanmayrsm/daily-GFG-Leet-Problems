class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, HashMap<String, Double>> graph = new HashMap<>();
        int i = 0;
        for(List<String> ls : equations){
            if(!graph.containsKey(ls.get(0))) graph.put(ls.get(0),  new HashMap<>());
            graph.get(ls.get(0)).put(ls.get(1), values[i]);
            if(!graph.containsKey(ls.get(1))) graph.put(ls.get(1),  new HashMap<>());
            graph.get(ls.get(1)).put(ls.get(0), (double)1/values[i]);
            i++;
        }
        double[] output = new double[queries.size()];
        for(i = 0; i<queries.size(); i++){
            if(graph.containsKey(queries.get(i).get(0)) && graph.containsKey(queries.get(i).get(1))){
                HashSet<String> vis = new HashSet<>();
                output[i] = dfs(queries.get(i).get(0), queries.get(i).get(1), 1, graph, vis);
            } else {
                output[i] = -1;
            }
        }
        return output;
    }
    private double dfs(String s, String d, double cost, HashMap<String, HashMap<String, Double>> graph, HashSet<String> vis){
        if(s.equals(d)) return cost;
        if(vis.contains(s)) return -1;
        vis.add(s);
        for(Map.Entry<String, Double> entry : graph.get(s).entrySet()){
            double res = dfs(entry.getKey(), d, cost * entry.getValue(), graph, vis);
            if(res != -1) return res;
        }
        return -1;
    }
}