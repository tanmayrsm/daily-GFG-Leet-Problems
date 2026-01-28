class Solution {
    public static int sumSubstrings(String s) {
        int n=s.length();
        int ans=0;
        for(int i=0;i<n;i++){
            int sum=0;
            for(int j=i;j<n;j++){
                sum=sum*10+(s.charAt(j)-'0');
                ans+=sum;
            }
        }
        return ans;
    }
}