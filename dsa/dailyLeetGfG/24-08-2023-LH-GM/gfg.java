
class Solution
{
    public String multiplyStrings(String s1,String s2)
    {
        //code here.
        // for simplicaity, consider only positive no multiplication
        String s1c = new String(s1.charAt(0) == '-' ? s1.substring(1) : s1);
        String s2c = new String(s2.charAt(0) == '-' ? s2.substring(1) : s2);
        int l1 = s1c.length();
        int l2 = s2c.length();
        
        int[] res = new int[l1 + l2];   // array to store nos of previous digit multiplication
        int l = 0, r = 0;
        
        for(int i = s1c.length() - 1; i >= 0; i--) {
            int carry = 0;
            int n1 = s1c.charAt(i) - '0';
            r = 0;
            for(int j = s2c.length() - 1; j >= 0; j--) {
                int n2 = s2c.charAt(j) - '0';
                int sum = n1 * n2 + carry + res[l + r];
                
                carry = sum / 10;
                res[l + r] = sum % 10;
                r++;
            }
            if(carry > 0)
                res[l + r] += carry;
            l++;
        }
        
        // ignore 0s from right side
        int i = res.length - 1;
        while(i >= 0 && res[i] == 0)    i--;
        
        if(i == -1) return "0";
        
        StringBuilder sb = new StringBuilder("");
        if((s1.charAt(0) == '-' && s2.charAt(0) != '-') || (s1.charAt(0) != '-' && s2.charAt(0) == '-'))
            sb.append("-");
            
        while(i >= 0) {
            sb.append(String.valueOf(res[i--]));
        }
        
        return sb.toString();
    }
}