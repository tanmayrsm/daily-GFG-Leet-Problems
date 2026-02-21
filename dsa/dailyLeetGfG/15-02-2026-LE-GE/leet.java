class Solution {
    public String addBinary(String a, String b) {
        int n = a.length() - 1, m = b.length() - 1, carry = 0;
        StringBuilder sb = new StringBuilder();
        while(n >= 0 && m >= 0) {
            int ac = (int)(a.charAt(n--) - '0'), bc = (int)(b.charAt(m--) - '0');
            carry = adder(ac + bc + carry, sb);
        }

        while(n >= 0) {
            int ac = a.charAt(n--) - '0', bc = carry;
            carry = adder(ac + bc, sb);
        }
        while(m >= 0) {
            int ac = b.charAt(m--) - '0', bc = carry;
            carry = adder(ac + bc, sb);
        }

        if(carry == 1)  sb.append("1");
        return sb.reverse().toString();
    }
    private int adder(int res, StringBuilder sb) {
        if(res == 3) {
            sb.append("1");
            return 1;
        } else if (res == 2) {
            sb.append("0");
            return 1;
        } else if (res == 1) {
            sb.append("1");
            return 0;
        } else {
            sb.append("0");
            return 0;
        }
    }
}