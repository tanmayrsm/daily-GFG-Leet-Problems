class Solution {
    public String largestGoodInteger(String num) {
        int n = num.length();
        int repeated = -1;
        for(int i = 0; i <= n - 3; i++) {
            String j = num.substring(i, i + 3);
            Set<Character> unique = j.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toSet());
            if(unique.size() == 1){  
                repeated = Math.max(repeated, Integer.parseInt(j.charAt(0) + ""));
            }
        }
        if(repeated == -1)
            return "";
            
        return new StringBuilder().append(String.valueOf(repeated).repeat(3)).toString();

    }
}