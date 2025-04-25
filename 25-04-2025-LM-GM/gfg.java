
class Solution {
    static int majorityElement(int arr[]) {
        // code here
        int vote=0, n = arr.length;
        int element=-1;
        for(int i=0;i<n;i++){
            if(vote==0){
                vote=1;
                element=arr[i];
            }
            else if(arr[i]==element){
                vote++;
            }
            else{
                vote--;
            }
          
        }
        vote=0;
        for(int i=0;i<n;i++){
            if(arr[i]==element){
                vote++;
            }
        }
        
        return vote > n/2 ? element : -1;
        
    }
}