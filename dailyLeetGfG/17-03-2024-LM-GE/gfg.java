
class Solution {

    public static int countPairs(LinkedList<Integer> head1, LinkedList<Integer> head2,
                          int x) {
        // add your code here
        int ans = 0;
        Set<Integer> st1 = new HashSet<>();
        for(Integer i : head1)
            st1.add(i);
        
        for(Integer i : head2) {
            if(st1.contains(x - i))
                ans++;
        }
        return ans;
    }
}
