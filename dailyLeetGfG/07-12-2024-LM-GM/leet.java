class Solution {

  private boolean isPossibleToDivideIntoBags(int[] nums, int maxCapacity, int maxOperations) {
      int totalOperations = 0;
      for (int capacity : nums) {
          // Considering the following examples.
          // (11, 3) -> (3 8) -> (3, (3, 5)) -> (3, (3, (3, 2)))
          // (13, 3) -> (10, 3) -> ((7, 3), 3) -> (((4, 3), 3), 3) -> ((((1, 3), 3), 3), 3)
          // (9, 3) -> (6, 3) -> ((3, 3), 3)

          // From the examples we can see that when the capacity is divisible by maxCapacity
          // then totalOperations required is the `quotient - 1`, else it is `quotient`.
          // We can simplify this condition by just reducing `1` from the capacity while
          // getting the result of the integer division.
          totalOperations += (capacity - 1) / maxCapacity;
          
      }

      // finally it will be possible to get the bag with max capacity only when the total
      // operations to have the max capacity of each bag equal to `maxOperation` doesn't
      // exceed maxOperations.
      return totalOperations <= maxOperations;
  }

  public int minimumSize(int[] nums, int maxOperations) {
      // nums array where nums[i] represent number of balls in a bag
      // I can perform operations at most maxOperation times

      // Now one thing is for sure, if I reduce the number of balls in a bag
      // then I will need more bags.

      // Now penalty represents the maximum number of balls in a bag
      // And I need to minimize the penalty.

      // Now let us consider the following facts.
      // Let say we were able to get a maximum number `p` representing the maximum
      // number of balls in a bag, after performing at most `maxOperations`

      // Then we can also get `p+1` maximum number of balls in a bag as well
      // by performing at most `maxOperations`. Because, here our constraints are relaxed.

      // So, we just need to find the lower bound of the maximum number of balls
      // that can be present in the bag, that we can get after performing at most `maxOperations`.

      // Henceforth, by getting the lower bound, we are sure that this number will also
      // give me the minimum possible penalty as well.

      // Henceforth, as the observation is kind of monotonically increasing, we can thereby
      // use binary search in order to get to our solution

      int maxElement = 0;

      for (int el : nums) {
          maxElement = Math.max(maxElement, el);
      }

      int l = 1, r = maxElement;

      int ans = maxElement;

      while (l <= r) {
          int mid = l + (r - l) / 2;
          if (isPossibleToDivideIntoBags(nums, mid, maxOperations)) {
              // we get one good solution
              // but let us see if we can get a better solution or not
              // So we will be doing the search on the lower half of the search space
              ans = mid;
              r = mid - 1;
          } else {
              // else no elements including mid and below can satisfy the condition
              // so we choose an the upper half of the search space.
              l = mid + 1;
          }
      }

      return ans;
  }
}