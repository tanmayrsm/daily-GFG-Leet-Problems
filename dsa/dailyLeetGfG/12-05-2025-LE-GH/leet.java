import java.util.Arrays;
import java.util.HashSet;

class Solution {
    public int[] findEvenNumbers(int[] digits) {
        int n = digits.length;
        Set<Integer> ans = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (digits[i] != 0) {
                for (int j = 0; j < n; j++) {
                    if (j != i)
                        for (int k = 0; k < n; k++) {
                            if (k != i && k != j && digits[k] % 2 == 0) {
                                int num = digits[i] * 100 + digits[j] * 10 + digits[k];
                                ans.add(num);
                            }
                        }
                }
            }
        }
        int[] ret = ans.stream().mapToInt(i -> i).toArray();
        Arrays.sort(ret);
        return ret;
    }
}