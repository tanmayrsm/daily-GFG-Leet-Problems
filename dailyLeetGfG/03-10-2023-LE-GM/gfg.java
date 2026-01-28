
class Solution
{
    String colName (long n)
    {
        // your code here
        StringBuilder sb = new StringBuilder("");
        while(n > 0) {
            long elem = (n % 26) - 1;
            if(elem < 0) {  // why this if
                elem = 25;  // take example of 52 OR 702
                n--;        // 52 is AZ, not ZZ
            }
            sb.append(String.valueOf((char)(elem + 'A')));
            n /= 26;
        }
        
        return sb.reverse().toString();
    }
}