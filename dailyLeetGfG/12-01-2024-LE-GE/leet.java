class Solution {
    private int noOfVowels(String s) {
        int ct = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char r = Character.toLowerCase(s.charAt(i));
            if(r == 'a' || r == 'e'|| r == 'i'|| r == 'o'|| r == 'u') {
                ct++;
            }
            
        }
        return ct;
    }
    public boolean halvesAreAlike(String s) {
        int n = s.length();
        
        return noOfVowels(s.substring(0, n/2)) == noOfVowels(s.substring(n/2, n));
    }
}