class Solution {
    public boolean backspaceCompare(String s, String t) {
        return removeBackSpace(s).equals(removeBackSpace(t));
    }

    private String removeBackSpace(String s) {
        StringBuilder x = new StringBuilder("");
        int n = s.length();
        for(int i = n - 1; i >= 0; ) {  // traverse from right to left
            char curr = s.charAt(i);
            int ct = 0;
            if(curr == '#') {
                while(i >= 0 && s.charAt(i) == '#') {
                    ct++;
                    i--;
                }

                // usecase - "bxo#j##tw" => "btw"
                while(i >= 0 && ct > 0) {
                    if(s.charAt(i) == '#')
                        ct++;
                    else ct--;
                    i--;
                }
            } else {
                x.append(curr + "");
                i--;
            }
        }
        // System.out.println("x :: " + x.reverse().toString());
        return x.reverse().toString();
    }
}