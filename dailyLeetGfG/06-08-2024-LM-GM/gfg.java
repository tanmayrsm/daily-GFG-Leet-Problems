

class Solution {

    public boolean isValid(String str) {
        // Write your code here
        String[] res = str.split("\\.");
        if (res.length != 4)    
            return false;
        for (String r : res) {
            if (r.isEmpty() || (r.length() > 1 && r.charAt(0) == '0'))
                return false;
            int no = Integer.parseInt(r);
            if (no < 0 || no > 255)
                return false;
        }
        return true;
    }
}