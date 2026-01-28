
class Solution
{
    //Function to check if a string is Pangram or not.
    public static boolean checkPangram  (String s) {
        // your code here
        int n = s.length();
        Set<Character> uniqueChars = new HashSet<>();
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if(Character.isUpperCase(c))
                c = Character.toLowerCase(c);
            if(Character.isLetter(c)) {
                // System.out.println("adding ::" + c);
                uniqueChars.add(c);
                if(uniqueChars.size() == 26)    
                    return true;
            }
        }
        return false;
    }
}