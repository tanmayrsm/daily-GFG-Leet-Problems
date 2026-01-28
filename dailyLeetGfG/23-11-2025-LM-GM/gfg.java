class Solution {
     int findParent(int u, int[] par)
    {
        if(u==par[u])
        return u;
        par[u] = findParent(par[u], par);
        return par[u];
    }
    void union(int a, int b, int[] par, int[] size)
    {
        int ap = findParent(a, par);
        int bp = findParent(b, par);
        if(ap==bp)
        return;
        if(size[ap]>=size[bp])
        {
            par[bp] =ap;
            size[ap] += size[bp];
        }
        else
        {
            par[ap] = bp;
            size[bp] += size[ap];
        }
    }
    int maxRemove(int[][] stones) 
    {
        int n = stones.length;
        int maxrow = 0;
        int maxcol = 0;
        for(int i=0;i<n;i++)
        {
            maxrow = Math.max(maxrow, stones[i][0]);
            maxcol = Math.max(maxcol, stones[i][1]);
        }
        int[] par = new int[maxrow+maxcol+2];
        int[] size = new int[maxrow+maxcol+2];
        for(int i=0;i<par.length;i++)
        par[i]=i;
        Arrays.fill(size,1);
        for(int i=0;i<n;i++)
        {
            int rownode = stones[i][0];
            int colnode = stones[i][1]+maxrow+1;
            union(rownode,colnode,par,size);
        }
        int count=0;
        for(int i=0;i<size.length;i++)
        {
            if(size[i]!=1 && par[i]==i)
            count++;
        }
        return n-count;
    }
};
