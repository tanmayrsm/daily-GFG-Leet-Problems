
//User function Template for Java

class Solution{
    static ArrayList<Integer> recamanSequence(int n){
        // code here
        ArrayList<Integer> arr = new ArrayList<>();
        Set<Integer> ht = new HashSet<>();
        arr.add(0);
        int currSize = 1;
        for(int i = 1; i < n; i++) {
            int last = arr.get(currSize - 1);
            if(last - i > 0 && !ht.contains(last - i))    arr.add(last - i);
            else arr.add(last + i);
            currSize++;
            ht.add(arr.get(currSize - 1));
        }
        return arr;
    }
}