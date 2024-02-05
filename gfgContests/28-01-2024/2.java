class Solution 
{
    public static int maximumBattalions(int N, String[] names) {
        // code here
        Map<String, Integer> mapper = new HashMap<>();
        int battalions = 0, maxIndex = -1;
        for(int i = 0; i < names.length; i++) {
            if(!mapper.containsKey(names[i]))
                mapper.put(names[i], -1);
            mapper.put(names[i], i);
        }

        for(int i = 0; i < names.length; i++) {
            if(i > maxIndex) {
                battalions++;
            }
            maxIndex = Math.max(maxIndex, mapper.get(names[i]));
        }

        return battalions;
        
    }
}

// string : lastIndex
// loop thru arr
//  check if currIndex > maxLastIndex
//      if yes -> battalion++
//      if no ->
//          maxIndex = max(maxIndex, lastIndex of currString)