class Solution {
    public String largestNumber(int[] nums) {
        StringBuilder sb = new StringBuilder();
        Integer[] arr = new Integer[nums.length];
        int k = 0;
        for(Integer num : nums)
            arr[k++] = num;
        Arrays.sort(arr, (Integer a, Integer b) -> {
            if (a.equals(b)) return 0;
            String s1 = String.valueOf(a), s2 = String.valueOf(b);
            int n = s1.length(), m = s2.length(), l = 0, r = 0;
            while(l < n && r < m) {
                char c1 = s1.charAt(l), c2 = s2.charAt(r);
                if(c1 == c2) {
                    l++;
                    r++;
                    // follow circularly, eevery edge case will be covered
                    if (l == n && r < m)    
                        l = l % n;
                    else if (r == m && l < n)
                        r = r % m;
                }
                else return Integer.compare((int)(c2 - '0'), (int)(c1 - '0'));
            }
            return 0;
        });
        
        if (arr[0] == 0)    // all nos are 0 in arr
            return "0";

        for(int num : arr) {
            sb.append(String.valueOf(num));
        }
        return sb.toString();
    }
}