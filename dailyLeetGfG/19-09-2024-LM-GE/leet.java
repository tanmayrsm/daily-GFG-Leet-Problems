class Solution {    // reff
    // Map to store results of sub-expressions to avoid recalculations
    private Map<String, List<Integer>> memo = new HashMap<>();
    public List<Integer> diffWaysToCompute(String expression) {
        // If the expression has already been computed, return the cached result
        if (memo.containsKey(expression)) {
            return memo.get(expression);
        }

        List<Integer> result = new ArrayList<>();
        
        // Iterate through each character in the expression
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            
            // Check if the character is an operator
            if (c == '+' || c == '-' || c == '*') {
                // Divide the expression into left and right parts around the operator
                String left = expression.substring(0, i);
                String right = expression.substring(i + 1);
                
                // Recursively compute the results of the left and right parts
                List<Integer> leftResults = diffWaysToCompute(left);
                List<Integer> rightResults = diffWaysToCompute(right);
                
                // Combine the results using the current operator
                for (int l : leftResults) {
                    for (int r : rightResults) {
                        if (c == '+') {
                            result.add(l + r);
                        } else if (c == '-') {
                            result.add(l - r);
                        } else if (c == '*') {
                            result.add(l * r);
                        }
                    }
                }
            }
        }

        // If no operators found, the expression is a single number, add it to the result
        if (result.isEmpty()) {
            result.add(Integer.parseInt(expression));
        }

        // Cache the computed result for the current expression
        memo.put(expression, result);
        return result;
    }
}