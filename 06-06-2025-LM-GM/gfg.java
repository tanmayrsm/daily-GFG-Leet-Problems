class Solution
{

    private static int PRIME = 256, q = 101, h;
    ArrayList<Integer> search(String pattern, String text)
    {
        // your code here
        int patternLength = pattern.length();
        double patternHash = getHash(pattern);
        h = 1;
        // The value of h would be "pow(PRIME, patternLength-1)%q"
        for (int i = 0; i < patternLength - 1; i++)
            h = (h * PRIME) % q;

        double textHash = getHash(text.substring(0, patternLength));
        ArrayList<Integer> ans = new ArrayList<>();

        for(int i=0; i<= text.length() - patternLength; i++) {
            if(textHash == patternHash) {
                if(text.substring(i, i+patternLength).equals(pattern)) {
                    ans.add(i + 1);
                }
            }

            if (i < text.length() - patternLength) {
                textHash = updateHash(text.charAt(i), text.charAt(i + patternLength), patternLength, textHash);
            }
        }
        return ans;
    }

    private static double getHash(String s) {
        int n = s.length();
        double hash = 0;
        for(int i = 0; i < n; i++)
            hash = (PRIME * hash + s.charAt(i)) % q;
        return hash;
    }

    private static double updateHash(char oldChar, char newChar, int patternLength, double oldHash) {
        double newHash = (PRIME * (oldHash - oldChar * h) + newChar) % q;
        if(newHash < 0)
            newHash += q;
        return newHash;

    }

}