
class Solution{
    static String findLargest(int N, int S){
        // code here
        if(S == 0 && N > 1)  return  "-1";
        
        StringBuilder sb = new StringBuilder("");
        while(S > 0 && sb.length() < N) {
            if(S < 10) {
                sb.append(String.valueOf(S));
                S = 0;
            } else {
                S -= 9;
                sb.append("9");
            }
        }
        
        if(S > 0)   return "-1";
        
        while(sb.length() < N)
            sb.append("0");
            
        return sb.toString();
        
    }
}