
class Solution {
    public static ArrayList<Integer> alternateSort(int[] arr) {

        // Your code goes here
        Arrays.sort(arr);  // first sort the arr
        
        ArrayList<Integer> ans = new ArrayList<>();  // make new ArrayList to store  
           int n=arr.length;
           int i=0;  // start
           int j= arr.length-1; // end
           
           while(i<j){ // add alternate elments highest lowest 
            
               ans.add(arr[j]);
                  ans.add(arr[i]);
               i++;
               j--;
           }
           
           
           
           // if we get odd length of arr then put middle element at the last adnd then soort
           if(n%2 != 0)
          ans.add(arr[n/2]);
          return ans;
    }
}