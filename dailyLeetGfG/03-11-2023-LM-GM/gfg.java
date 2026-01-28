

class Solution {
    boolean checkTriplet(int[] arr, int n) {
        // code here
        TreeSet<Integer> st = new TreeSet<>();
        for(int x :  arr)   st.add(x); 
        List<Integer> st2 = new ArrayList<>(st);
        
        for(int i = st2.size() - 1; i >= 2; i--) {
            int l = 0, r = i - 1;
            long cSquare = st2.get(i)*st2.get(i);
            while(l < r) {
                long aSquare = st2.get(l)*st2.get(l), 
                bSquare = st2.get(r)*st2.get(r);
                
                if( aSquare + bSquare > cSquare)  r--;
                else if (aSquare + bSquare < cSquare) l++;
                else if (aSquare + bSquare == cSquare)    return true;
            }
        }
        
        return false;
    }
}