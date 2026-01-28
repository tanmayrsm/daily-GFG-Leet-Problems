
//User function Template for Java

class Solution {
    String printLargest(int N, String[] arr) {
        // code here
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                String ab = a + b;
                String ba = b + a;
                // Compare which combination forms a larger number
                return ba.compareTo(ab);
            }
        });
        // for(String g : arr)
        //     System.out.print(g + "::");
        return String.join("", arr);
    }
}