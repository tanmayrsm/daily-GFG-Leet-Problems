class GfG
{
    public static ArrayList<ArrayList<Integer>> uniqueRow(int a[][],int r, int c)
    {
        //add code here.
        Set<String> s = new HashSet<>();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        
        for(int i = 0; i < r; i++) {
            StringBuilder ss = new StringBuilder();
            ArrayList<Integer> x = new ArrayList<>();
            
            for(int j = 0; j < c; j++) {
                ss.append(String.valueOf(a[i][j]));
                x.add(a[i][j]);
            }
            if(!s.contains(ss.toString())) {
                s.add(ss.toString());
                ans.add(x);
            }
        }
        
        return ans;
    }
}