class Solution {
    public:
      vector<int> dijkstra(int V, vector<vector<int>> &edges, int src) {
          // Code here
          vector<vector<pair<int, int>>> adj(V);
          for(auto it: edges)
          {
              adj[it[0]].push_back({it[1], it[2]});
              adj[it[1]].push_back({it[0], it[2]});
          }
          
          priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
          vector<int> dist(V, INT_MAX);
          dist[src]=0;
          pq.push({0,src});
          while(!pq.empty()){
              int dis = pq.top().first;
              int node = pq.top().second;
              pq.pop();
              
              for(auto it: adj[node]){
                  int v = it.first;
                  int w = it.second;
                  if(dis+w < dist[v]){
                      dist[v] = dis+w;
                      pq.push({dis+w,v});
                  }
              }
          }
          return dist;
      }
  };