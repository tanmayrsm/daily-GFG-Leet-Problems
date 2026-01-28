class Solution {
    String minSum(int[] arr) {
        // code here
        Arrays.sort(arr);
        int n = arr.length, carry = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = n - 1; i >= 0; i -= 2) {
            int d1 = arr[i], d2 = (i - 1) >= 0 ? arr[i - 1] : 0;
            int newer = d1 + d2 + carry;
            sb.append(newer % 10);
            carry = newer / 10;
        }
        if (carry == 1) sb.append("1");
        sb.reverse();

        // remove initial 0s
        int ct = 0, m = sb.length();
        while(ct < m) {
            if(sb.charAt(ct) == '0')
                ct++;
            else break;
        }
        return sb.substring(ct).toString();
    }
}