class Solution {
    ArrayList<Integer> findTwoElement(int arr[]) {
        // code here
        // code here
        long n = arr.length;
        ArrayList<Integer> ls = new ArrayList<>();

        // Sum formulas for 1 to n
        long aSumN = (n * (n + 1)) / 2;
        long aSumN_2 = (n * (n + 1) * (2 * n + 1)) / 6;

        long obSumN = 0;
        long obSumN_2 = 0;

        // Calculate observed sums
        for (int val : arr) {  // Bug #1: Changed long to int
            obSumN += val;
            obSumN_2 += (long)val * val;  // Bug #2: Cast to prevent overflow
        }

        // val1 = missing - repeating
        long val1 = aSumN - obSumN;

        // val2 = missing^2 - repeating^2
        long val2 = aSumN_2 - obSumN_2;

        // val2/val1 = (missing^2 - repeating^2)/(missing - repeating) = missing + repeating
        val2 = val2 / val1;

        // Now: val1 = missing - repeating, val2 = missing + repeating
        int missing = (int)((val1 + val2) / 2);
        int repeating = (int)(val2 - missing);

        ls.add(repeating);  // Bug #3: Add repeating first (based on typical problem requirement)
        ls.add(missing);

        return ls;
    }
}