// User function Template for Java
class Solution {
    // Function to find hIndex
    public int hIndex(int[] citations) {
        // code here
        int count=0;
        
        int n = citations.length;
        for(int i=n;i>0;i--){
            // if(citations[i]==0);
            int current = i;
            for(int j=0;j<n;j++){
                if(citations[j]>=current){
                    count++;
                }
            }
            if(count == current){
                return count;
            }
            else if(count >= current){
                return current;
            }
            else{
                count=0;
            }
        }
        return count;
    }
}