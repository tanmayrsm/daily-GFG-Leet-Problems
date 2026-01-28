
class Solution
{
    //Function to check if two strings are isomorphic.
    public static boolean areIsomorphic(String str1,String str2)
    {
        // Your code here
     int len1 = str1.length();
        int len2 = str2.length();
        if(len1 != len2) return false;
        HashMap<Character, Character> map1 = new HashMap<>();
        HashMap<Character, Character> map2 = new HashMap<>();
        for(int i=0; i<len1; i++){
            char ch1 = str1.charAt(i);
            char ch2 = str2.charAt(i);
            if(map1.containsKey(ch1)){
                if(map1.get(ch1) != ch2) return false;
            }
            else{
                map1.put(ch1, ch2);
            }
            if(map2.containsKey(ch2)){
                if(map2.get(ch2) != ch1) return false;
            }
            else{
                map2.put(ch2, ch1);
            }
        }
        return true;
    }
}