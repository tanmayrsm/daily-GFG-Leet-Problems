
class Solution {
    public static boolean makePalindrome(int n, String[] arr) {
        // code here
        Set<String> s = new HashSet<>();
        for(String a : arr) {
            String x = new StringBuilder(a).reverse().toString();
            if(s.contains(x)) { // if palin available, merge both
                s.remove(x);
            } else {
                s.add(a);
            }
        }
        if(s.size() == 1) {
            String val = s.stream().findFirst().get();
            return val.equals(new StringBuilder(val).reverse().toString());
        } else if(s.size() == 0)
            return true;
        return false;
    }
}
        
