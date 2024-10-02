class Solution {
    public static int rotateDelete(ArrayList<Integer> arr) { // reff
        // code here
        // getting intuoituin is difficule here, as question never states to do what if k > n
        // however, below pattern was observed by people
        boolean flag = true;
        int n = arr.size();
        int x = n / 2;
        
        while(x != 0){
            if(flag){
                n -= 1;
                flag = false;
            }else{
                n -= 2;
                flag = true;
            }
            x--;
        }
        return arr.get(n);
    }
}
