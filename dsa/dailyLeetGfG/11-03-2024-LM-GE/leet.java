class Solution {
    public String customSortString(String order, String s) {
        int[] orderIndex = new int[26];
        char[] sArr = s.toCharArray();
        int n = order.length(), m = sArr.length, ctr = 1;
        for(int i = 0; i < n; i++) {
            orderIndex[order.charAt(i) - 'a'] = ctr;
            ctr++;
        }

        return Arrays.stream(s.split(""))
          .sorted((a, b) -> Integer.compare(orderIndex[a.charAt(0) - 'a'], orderIndex[b.charAt(0) - 'a']))
         .collect(Collectors.joining());
        // Arrays.sort(sArr, (Character a, Character b) -> 
        //     Integer.compare(orderIndex[a - 'a'], orderIndex[b - 'a']));
        // return new String(sArr);
    }
}