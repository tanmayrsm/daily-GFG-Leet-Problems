class Solution {    // reff soln
    public int[] restoreArray(int[][] vals) {
        // Create a dictionary to store the mapping of elements to their adjacent elements.
        Map<Integer, int[]> pairs = new HashMap<>();

        // Build the mapping by iterating through the adjacent pairs.
        for (int i = 0; i < vals.length; i++) {
            // Update the mapping for the first element.
            if (pairs.containsKey(vals[i][0])) {
                pairs.get(vals[i][0])[1] = vals[i][1];
            } else {
                pairs.put(vals[i][0], new int[] {vals[i][1], -1000000});
            }
            
            // Update the mapping for the second element.
            if (pairs.containsKey(vals[i][1])) {
                pairs.get(vals[i][1])[1] = vals[i][0];
            } else {
                pairs.put(vals[i][1], new int[] {vals[i][0], -1000000});
            }
        }

        // Initialize the result array to store the original array.
        int[] result = new int[vals.length + 1];
        int start = -1000000;

        // Find the starting element with only one adjacent element.
        for (Map.Entry<Integer, int[]> entry : pairs.entrySet()) {
            if (entry.getValue()[1] == -1000000) {
                start = entry.getKey();
            }
        }
        result[0] = start;

        int left = -1000000;

        // Reconstruct the original array from the adjacent pairs.
        for (int i = 1; i < result.length; i++) {
            int[] val = pairs.get(start);
            int newval = (val[0] == left ? val[1] : val[0]);
            result[i] = newval;
            left = start;
            start = newval;
        }

        return result;
    }
}