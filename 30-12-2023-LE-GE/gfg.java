

class Solution
{
    //Function to return the name of candidate that received maximum votes.
    public static String[] winner(String arr[], int n)
    {
        // add your code
        int maxVote = 0;
        TreeMap<String, Integer> mp = new TreeMap<>();
        for(String c : arr) {
            mp.put(c, mp.getOrDefault(c, 0) + 1);
            maxVote = Math.max(maxVote, mp.get(c));
        }
        
        for(Map.Entry<String, Integer> m : mp.entrySet()) {
            if(m.getValue() == maxVote) {
                return new String[] {m.getKey(), String.valueOf(m.getValue()) };
            }
        }
        return new String[2];
    }
}

