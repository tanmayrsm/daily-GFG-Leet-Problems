class Solution
{
   
    String reverseEqn(String S)
    {
        // your code here
        Stack<StringBuilder> s = new Stack<>();
        char[] ch = S.toCharArray();
        int n = ch.length;
        int i = 0;
        while(i < n) {
            StringBuilder x = new StringBuilder("");
            if(Character.isDigit(ch[i])) {
                while(i < n && Character.isDigit(ch[i])) {
                    x.append(ch[i] + "");
                    i++;
                }
            } else {
                x.append(ch[i] + "");    
                i++;
            }
            s.push(x);
        }
        StringBuilder res = new StringBuilder("");
        while(!s.isEmpty()) {
            res.append(s.pop());
        }
        return res.toString();
    }
}