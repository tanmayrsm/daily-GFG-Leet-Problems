
class Solution
{
    //Function to find the first non-repeating character in a string.
    static char nonrepeatingCharacter(String S)
    {
        //Your code here
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        Set<Character> repeated = new HashSet<>();
        for(int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if(map.get(c) == null && !repeated.contains(c))
                map.put(c, 1);
            else { 
                map.remove(c);
                repeated.add(c);
            }
        }
        if(map.size() == 0)
            return '$';
            
        List<Character> lKeys = new ArrayList<>(map.keySet());
        return lKeys.get(0);
    }
}

