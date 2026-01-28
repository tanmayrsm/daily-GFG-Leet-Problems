class Lep
{
    class Payri {
        int index;
        long val;
        long prefixSum;
        Payri(int index, long val) {
            this.index = index;
            this.val = val;
        }
    }
    class SortByVal implements Comparator<Payri> {
        public int compare(Payri a, Payri b) {
            return Integer.valueOf((int)a.val - (int)b.val);
        }
    }
    public long[] smallerSum(int n,int arr[])
    {
        List<Payri> pairs = new ArrayList<>();
        long[] ans = new long[n];
        
        for(int i = 0; i < n; i++)
            pairs.add(new Payri(i, arr[i]));
        Collections.sort(pairs, new SortByVal());
        
        pairs.get(0).prefixSum  = pairs.get(0).val;
        
        long currSum = 0;
        for(int i = 1; i < n; i++) {
            if(pairs.get(i).val != pairs.get(i-1).val) {
                currSum =  pairs.get(i-1).prefixSum;
            }
            ans[pairs.get(i).index] = currSum;
            pairs.get(i).prefixSum = pairs.get(i).val + pairs.get(i-1).prefixSum;
        }
        return ans;
    }
}