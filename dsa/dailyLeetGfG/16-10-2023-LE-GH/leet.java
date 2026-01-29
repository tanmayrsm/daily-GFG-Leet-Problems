// 100% Beats
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();

        // Initialize the first element of the row to 1.
        result.add(1);

        // Calculate the `k`-th row using binomial coefficient formula.
        for (int i = 1; i <= rowIndex; i++) {
            // Use the previous element to calculate the current element.
            long currentElement = (long) result.get(i - 1) * (rowIndex - i + 1) / i;
            result.add((int) currentElement);
        }

        return result;
    }
}