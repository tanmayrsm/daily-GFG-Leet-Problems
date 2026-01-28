class Solution {
    static char[] cc;
    // static char[] newer;
    static boolean didStringChanged;
    public static ArrayList<Character> easyTask(int n,String s,int Q,query queries[])
    {
        cc = s.toCharArray();
        // newer = Arrays.copyOf(cc);
        didStringChanged = true;
        ArrayList<Character> ans = new ArrayList<>();
        for(int i = 0; i < Q; i++) {
            query q = queries[i];
            if(q.type.equals("1")) {
                didStringChanged = true;
                changeChar(Integer.valueOf(q.a), q.b.charAt(0));
                // newer = Arrays.copyOf(cc);
                // Arrays.sort(newer);
            } else {
                didStringChanged = false;
                ans.add(getLexiGreatest(Integer.valueOf(q.a), Integer.valueOf(q.b), Integer.valueOf(q.c)));
            }
        }
        return ans;
    }
    private static void changeChar(int index, char c) {
        cc[index] = c;
    }
    private static char getLexiGreatest(int l, int r, int k) {
        int[] chars = new int[26];
        for(int i = l; i <= r; i++) {
            chars[cc[i] - 'a']++;
        }
        int ct = 1;
        for(int i = 25; i >= 0; i--) {  // moving kind of in descending order
            while(chars[i] > 0) {
                if(ct == k) {
                    return (char)(i + 'a');
                }
                chars[i]--;
                k--;
            }
        }
        return 'a';
    }
}
/*In case the query is of type 1.
type=1
c=null
*/

/*In case the query is of type 2.
type=2
c=k
*/

class query
{
    String type;
    String a;
    String b;
    String c;
    public query(String type,String a,String b,String c)
    {
        this.type=type;
        this.a=a;
        this.b=b;
        this.c=c;
    }
}