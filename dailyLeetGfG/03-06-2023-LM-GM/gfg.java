
class Solution {
    public static int maxEqualSum(int N1,int N2,int N3, int[] S1, int[] S2, int[] S3) {
        // code here
        makePreSum(S1);
        makePreSum(S2);
        makePreSum(S3);
        
        int p1 = 0;
        int p2 = 0;
        int p3 = 0;
            
        while(p1 < N1 && p2 < N2 && p3 < N3) {
            // System.out.println(S1[p1] + " :: " + S2[p2] + " :: " + S3[p3]);
            if(S1[p1] == S2[p2] && S2[p2] == S3[p3])
                return S1[p1];
            else if(S1[p1] >= S2[p2] && S1[p1] >= S3[p3])
                p1++;
            else if(S2[p2] >= S1[p1] && S2[p2] >= S3[p3])
                p2++;
            else if(S3[p3] >= S2[p2] && S3[p3] >= S1[p1])
                p3++;
        }
        return 0;
    }
    private static void makePreSum(int[] x) {
        int n = x.length;
        for(int i = n - 2; i >= 0; i--) {
            x[i] += x[i+1];
        }
    }
}
        
