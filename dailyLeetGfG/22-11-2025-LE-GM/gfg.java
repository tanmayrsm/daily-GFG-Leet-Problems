
class Solution {
    static class UFDS {
        int parent[], rank[];
        public UFDS(int n)
        {
            parent=new int[n];
            rank=new int[n];

            for(int i=0;i<n;i++)
            {
                parent[i]=i;
            }
        }
        int find(int x)
        {
            if(x==parent[x])
                return x;
            return parent[x]=find(parent[x]);
        }
        void unionByRank(int x,int y)
        {
            int leaderofx=find(x);
            int leaderofy=find(y);

            if(leaderofx==leaderofy) return ;
            parent[leaderofy]=leaderofx;
        }
    }
    public static int minConnect(int n, int[][] connections) {
        UFDS obj=new UFDS(n);
        int extra=0;
        for(int a[]:connections)
        {
            int u=a[0];
            int v=a[1];
            if(obj.find(u)==obj.find(v))
            {
                extra++;
            }
            else
            {
                obj.unionByRank(u,v);

            }
        }
        int countultimateparents=0;
        for(int i=0;i<n;i++)
        {
            if(obj.parent[i]==i)
            {
                countultimateparents++;
            }
        }
        int ans=countultimateparents-1;
        if(extra>=ans)
            return ans;
        return -1;
    }
}