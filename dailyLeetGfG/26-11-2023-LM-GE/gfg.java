
class Solution{
    private static int adder;
    private static List<Integer> ans;
    public List<Integer> pattern(int N){
        // code here
        adder = -5;
        ans = new ArrayList<>();
        ans.add(N);
        if(N <= 0)  return ans;
        
        noLoop(N, N + adder);
        return ans;
    }
    private static void noLoop(int N, int copyN) {
        ans.add(copyN);
        if(N == copyN)  return;
        if(copyN <= 0) {
            adder = 5;
            noLoop(N, copyN + adder);
        } else {
            noLoop(N, copyN + adder);    
        }
    }
}