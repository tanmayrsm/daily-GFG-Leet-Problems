class Solution {
    public String findLargest(int[] arr) {
        // code here
        String[] arr2 = new String[arr.length];
        int k = 0;
        boolean allZero = true;
        for (int x : arr)  { 
            arr2[k++] = String.valueOf(x);
            if (x != 0) allZero = false;
        }
        if (allZero)    return "0";
        Arrays.sort(arr2, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                String ab = a + b;
                String ba = b + a;
                // Compare which combination forms a larger number
                return ba.compareTo(ab);
            }
        });
        return String.join("", arr2);
    }
}