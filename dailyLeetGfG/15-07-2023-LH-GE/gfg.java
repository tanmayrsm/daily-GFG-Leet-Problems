
class Solution
{
    //Function to delete middle element of a stack.
    public void deleteMid(Stack<Integer>s,int n){
        // code here
        Stack<Integer> temp = new Stack<>();
        int ct = 0;
        while(ct != n / 2) {
            temp.push(s.pop());
            ct++;
        }
        s.pop();
        
        while(!temp.isEmpty())
            s.push(temp.pop());
    } 
}
