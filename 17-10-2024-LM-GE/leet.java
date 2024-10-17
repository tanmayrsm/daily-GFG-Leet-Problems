class Solution {
    public int maximumSwap(int num) {
        // approach 1 -> sort num in desc order
        //            ->  put first msimatching char from left to right,
        //            -> char which was swapped, put it in place of new char

        // appraoch 2 -> get max character in num (not from index 0, but post index where ascension is seen)
        // from L->R, replace first mismatched smaller char here, and at its place put old
        // eg -> 44394, 9 is max (ascension from 3, smallerIndex = 2, maxIndex = 3)
        // from 0 to smallerIndex, if no < nums[maxIndex] swap both and return
        // 4 < 9 => 94344

        // int num => int[] valu
        String str = String.valueOf(num);
        int n = str.length(), maxo = -1, maxIndex = -1, smallerIndex = -1;

        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            Integer currNo = Integer.parseInt(nums.chart(i) + "");
            nums[i] = currNo;
        }

        // get ascension index -> smallerIndex, and a maxIndex post that
        for(int i = 0; i < n; i++) {
            if(maxo == -1 && i + 1 < n && nums[i] < nums[i + 1]) {
                maxo = nums[i + 1];
                maxIndex = i + 1;
                smallerIndex = i;
            } else if(maxo != -1 && nums[i] > maxo) {
                maxo = nums[i];
                maxIndex = i;
            }
            nums[i] = currNo;
        }

        // swap maxIndex with a no from 0 to smallerIndex once 
        if(maxo != -1) {
            for(int i = 0; i <= smallerIndex; i++) {
                if(nums[i] < nums[maxIndex]) {
                    int temp = nums[i];
                    nums[i] = nums[maxIndex];
                    nums[maxIndex] = temp;
                    break;
                }
            }
        } else return num;

        // int[] num => integer value
        StringBuilder sb = new StringBuilder();
        for(int numo : nums)
            sb.append(String.valueOf(numo));

        return Integer.parseInt(sb.toString());
        

    }
}