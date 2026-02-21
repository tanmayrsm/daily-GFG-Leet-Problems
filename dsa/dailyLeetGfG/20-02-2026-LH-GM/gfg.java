class Solution {
    public String findLargest(int[] arr) {
        // code here
        String[] nums = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nums[i] = String.valueOf(arr[i]);
        }

        // 2. Sort so that (b+a) comes before (a+b) if it makes a bigger number
        Arrays.sort(nums, (a, b) -> String.valueOf(b + a).compareTo(String.valueOf(a + b)));

        StringBuilder sb = new StringBuilder();
        boolean zero = true;
        for(String x :  nums) {
            if(!x.equals("0")) {
                zero = false;
            }
            if(!zero)
                sb.append(String.valueOf(x));
        }
        if(sb.isEmpty())    return "0";
        return sb.toString();
    }
}