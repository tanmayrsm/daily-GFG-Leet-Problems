
class Solution {
    // Function to find the majority elements in the array
    public List<Integer> findMajority(List<Integer> nums) { // reff
        int count1 = 0, count2 = 0, candidate1 = 0, candidate2 = 1;
        for (int num : nums) {
            if (num == candidate1) count1++;
            else if (num == candidate2) count2++;
            else if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == candidate1) count1++;
            else if (num == candidate2) count2++;
        }
        List<Integer> result = new ArrayList<>();
        int n = nums.size();
        if (count1 > n / 3) result.add(candidate1);
        if (count2 > n / 3) result.add(candidate2);
        // sort the result based on the first occurrence in the input array
        Collections.sort(result, (a, b) -> nums.indexOf(a) - nums.indexOf(b));
        return result.isEmpty() ? Arrays.asList(-1) : result;
    }
}
