
class Solution {

    static int sameOccurrence(int arr[], int x, int y) {
        // write code here
        // similar to question - find all subarrays with equal no of 0s, 1s
        // where u use 0-> -1, 1 -> 1, and a hashmap to track its last index
        
        int n=arr.length;
        HashMap<Integer,Integer>map = new HashMap<>();
        
        for(int i=0;i<n;i++){
            if(arr[i]==x) arr[i]=1;
            else if(arr[i]==y) arr[i]=-1;
            else arr[i]=0;
        }
        int ans=0;
        int sum=0;
        map.put(0,1);
        for(int i=0;i<n;i++){
            sum+=arr[i];
            
            if(!map.containsKey(sum)){
                map.put(sum,1);
            }else{
                ans+=map.get(sum);
                map.put(sum,map.get(sum)+1);
                
            }
        }
        return ans;
    }
}