class Solution {
    public List<String> letterCombinations(String digits) {
        String[] inp = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> ans = new ArrayList<>();
        int n = digits.length();
        
        if(n == 0)  return ans;
        int[] dig = new int[n];

        for(int i = 0; i < n; i++)
            dig[i] = digits.charAt(i) - '0';
        
        ans = getList(inp[dig[0]]);
        if(n == 1)  return ans;
        List<String> pp = getList(inp[dig[1]]);

        for(int i = 1; i < n ; i++) {
            ans = mul(ans, pp);
            if(i + 1 < n) {
                pp = getList(inp[dig[i + 1]]);
            }
        }
        // 2,3,4
        // 2 * f(3,4)
        // 2 * 3 * f(4)
        // 2 * 3 * "ghi"
        // 2 * (def * ghi) => [dg, dh, di, eg, eh, ei, fg,fh, fi]
        // [d,e,f] * [dg, dh, di, eg, eh, ei, fg,fh, fi]
        return ans;

    }

    private static List<String> getList(String m) {
        List<String> rem = new ArrayList<>();
        char[] x = m.toCharArray();
        for(char c : x)
            rem.add(String.valueOf(c));
        return rem;
    }

    private static List<String> mul(List<String> a, List<String> b) {
        List<String> ans = new ArrayList<>();
        for(int i = 0; i < a.size(); i++) {
            for(int j = 0; j < b.size(); j++) {
                StringBuilder bs = new StringBuilder(a.get(i));
                bs.append(b.get(j));
                ans.add(bs.toString());
            }
        }
        return ans;
    }
}