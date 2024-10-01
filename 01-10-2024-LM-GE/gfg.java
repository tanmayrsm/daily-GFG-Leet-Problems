class Solution {
    public long multiplyTwoLists(Node first, Node second) {
        long MOD = 1000000007;
        
        long num1 = 0;
        while(first != null){
            num1 = (num1*10 + first.data) % MOD;
            first = first.next;
        }
        
        long num2 = 0;
        while(second != null){
            num2 = (num2*10 + second.data) % MOD;
            second = second.next;
        }
        
        return (num1*num2) % MOD;
    }
}