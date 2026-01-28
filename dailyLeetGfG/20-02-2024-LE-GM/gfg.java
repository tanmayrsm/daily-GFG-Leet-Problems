
//User function Template for Java
class Solution
{
    private static int n;
    public static int wordBreak(String A, ArrayList<String> B) {
        //code here
        n = A.length();
        boolean canMake = solve(0, new StringBuilder(), new HashSet<>(B), A);
        return canMake ? 1 : 0;
    }
    private static boolean solve(int curr, StringBuilder sb, Set<String> B, String A) {
        if(curr == n) {
            return B.contains(sb.toString());
        }
        // take curr char
        boolean take = false, nottake = false;
        if(B.contains(sb.toString())) {
            take = solve(curr + 1, new StringBuilder(A.charAt(curr) + ""), B, A);
        }
        nottake = solve(curr + 1, new StringBuilder(sb).append(A.charAt(curr) + ""), B, A);
        return take || nottake;
    }
}