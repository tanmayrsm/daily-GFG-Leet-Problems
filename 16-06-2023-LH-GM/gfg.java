
class Solution {    // referred code
    static Map<Integer,Integer> mm1;
    static Map<Integer,Integer> mm2;
    static List<Integer> temp;
    static long help(int pos,int index,long dp[][],int indication){
        if(index==temp.size())return 0;
        if(dp[index][indication]!=-1)return dp[index][indication];
        int left = mm1.get(temp.get(index));
        int right = mm2.get(temp.get(index));
        long one=0,two=0;
        one = Math.abs(pos-left)+Math.abs(left-right)+help(right,index+1,dp,1);
        if(index==temp.size()-1)one+=Math.abs(right-0);
        two = Math.abs(pos-right)+Math.abs(right-left)+help(left,index+1,dp,0);
        if(index==temp.size()-1)two+=Math.abs(left-0);
        return dp[index][indication]= Math.min(one,two);
    }
    public static long minTime(int n, int[] locations, int[] types) {
        // code here
        temp = new ArrayList<>();
        mm1 = new HashMap<>();
        mm2 = new HashMap<>();
        int mx=Integer.MIN_VALUE;
        for(int i=0;i<n;i++)mx=Math.max(mx,types[i]);
        int ele[] = new int[mx+1];
        Arrays.fill(ele,0);
        for(int i=0;i<n;i++){
            if(mm1.get(types[i])==null){
                ele[types[i]]=1;
                mm1.put(types[i],locations[i]);
                mm2.put(types[i],locations[i]);
            }
            else{
                mm1.put(types[i],Math.min(mm1.get(types[i]),locations[i]));
                mm2.put(types[i],Math.max(mm2.get(types[i]),locations[i]));
            }
        }
        for(int i=0;i<=mx;i++){
            if(ele[i]==1)temp.add(i);
        } 
        int a=temp.size();
        long dp[][]=new long[a][2];
        for(long t[]:dp){
            Arrays.fill(t,-1);
        }
        return help(0,0,dp,0);
    }
}
        
