class Solution {
    public boolean findTriplet(int[] arr) {
        HashSet<Integer> fmap = new HashSet<>();
        for(int far:arr){
            fmap.add(far);
        }
        int i=0, j = arr.length-1;
        while(i<arr.length){
            if(i<j){
                if(fmap.contains(arr[i]+arr[j])){
                    return true;
                }
                j--;
            }else{
                i++;
                j = arr.length-1;
            }
        }
        return false;
    }
}