class Solution {
    public int sumFourDivisors(int[] nums) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int count = 0;
            int sumOfDivisors = 0;
            for(int j = 1; j <= Math.sqrt(num); j++) {
                if(num % j == 0) {
                    if(j * j == num) {
                        count++;
                        sumOfDivisors += j;
                    } else {
                        count += 2;
                        sumOfDivisors += (j + (num / j));
                    }
                }
            }
            if(count == 4) {
                sum += sumOfDivisors;
            }
        }
        return sum;
    }
}