class Solution {
    public int kokoEat(int[] arr, int k) {
        // code here
        // code here

        // Define range of Binary Search(1,arr.MAX)

        int low = 1;

        // Find the value of arr.MAX
        int high = Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            if(arr[i]>high){
                high = arr[i];
            }
        }

        // value of s
        int s = 0;

        // Apply Binary Search
        while(low<=high){

            int mid = (low+high)/2;

            int hours = 0;

            // Calculate no of hours taken with s = mid bananas per hour
            for(int i:arr){
                hours += (int) Math.ceil((double) i / mid);
            }


            // If solution found, search Left for lesser value of s/mid
            if(hours <= k){

                // Assign value of mid to result s
                s = mid;

                //Search Left for lower value of s

                high = mid - 1;

                // If solution does not found (hours > k), search Right for larger value of s
            }else{

                //Search Right
                low = mid+1;
            }

        }

        return s;
    }
}
