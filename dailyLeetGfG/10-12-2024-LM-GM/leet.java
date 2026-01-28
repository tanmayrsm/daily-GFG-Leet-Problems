class Solution {
    public int maximumLength(String s) {
        Map<String, Integer> map = new HashMap<>();
        int n = s.length();

        for (int i = 0; i < n; i++) {
            int val = (int) s.charAt(i);
            StringBuilder sb = new StringBuilder();

            for (int j = i; j < n; j++) {
                if ((int) s.charAt(j) == val) {
                    sb.append(s.charAt(i));
                    String st = sb.toString();
                    map.put(st,map.getOrDefault(st,0)+1);
                }else{
                    break;
                }
            }
        }

        int maxLen = 0;
        int count = 3;

        for (String key : map.keySet()) {

            if (map.get(key) >= count && key.length() > maxLen) {
                maxLen = key.length();
            }
        }
        return maxLen == 0 ? -1 : maxLen;
    }
}