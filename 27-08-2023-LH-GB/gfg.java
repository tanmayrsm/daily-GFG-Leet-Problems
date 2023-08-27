
class Reverse
{
    // Complete the function
    // str: input string
    public static String reverseWord(String str)
    {
        // Reverse the string str
        char[] s = str.toCharArray();
        int l = 0, r = s.length - 1;
        while(l < r) {
            char temp = s[l];
            s[l] = s[r];
            s[r] = temp;
            l++;
            r--;
        }
        return new String(s);
    }
}