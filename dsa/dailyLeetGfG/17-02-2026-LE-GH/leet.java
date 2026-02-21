class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> result = new ArrayList<>();

        // Optimization: Max bits possible is 8 (11:59 -> 3 bits + 5 bits)
        // If turnedOn is > 8, no solution is possible.
        if (turnedOn > 8) return result;

        // Iterate through all possible hours (0-11)
        for (int h = 0; h < 12; h++) {
            // Iterate through all possible minutes (0-59)
            for (int m = 0; m < 60; m++) {

                // Check if the total number of set bits equals turnedOn
                if (Integer.bitCount(h) + Integer.bitCount(m) == turnedOn) {

                    // Format the string manually using StringBuilder for performance
                    StringBuilder time = new StringBuilder();
                    time.append(h).append(":");

                    // Handle leading zero for minutes
                    if (m < 10) {
                        time.append("0");
                    }
                    time.append(m);

                    result.add(time.toString());
                }
            }
        }
        return result;
    }
}