class Solution {
    public boolean doesValidArrayExist(int[] derived) {
        int n = derived.length;
        char[] custom = new char[n];
        // lets do some assume work, if derived[i] = '1', it means
        // let 'g' be at 'i', then 'i + 1' sure has 'y'
        // if derived[i + 1] = '1', and custom[i] = 'y', then custom[i + 1] = 'g'
        custom[0] = derived[0] == 1 ? 'g' : 'y';
        for (int i = 0; i < n - 1; i++) {
            if (derived[i] == 1) {
                custom[i + 1] = (custom[i] == 'g') ? 'y' : 'g';
            } else {
                custom[i + 1] = custom[i];
            }
        }
        // for (char c : custom)
        //     System.out.print(c + ":");
        
        if (derived[n - 1] == 0 && custom[n - 1] == custom[0])  return true;
        else if (derived[n - 1] == 1 && custom[n - 1] != custom[0]) return true;
        return false;
    }
}