
class Solution
{ 
    private static List<Integer> list;
    static void reverse(Stack<Integer> s)
    {
        // add your code here
        list = new ArrayList<>();
        recur(s);
    }
    private static int recur(Stack<Integer> s) {
        if(s.isEmpty()) return 0;
        list.add(s.pop());
        
        int k = recur(s);
        s.push(list.get(k));
        
        return k+1;
    }
}