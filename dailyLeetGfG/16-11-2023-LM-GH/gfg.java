
class Solution{ // referred solution - https://www.youtube.com/watch?v=uK6ehk8imUQ
    public static String findString(int n, int k) {
        Set<String> visited = new HashSet<>(); // Set to keep track of visited strings
        String startingNode = new String(new char[n - 1]).replace('\0', '0'); // Starting node with (n-1) '0' characters
        List<Integer> path = new ArrayList<>(); // List to store the DFS path
        dfs(k, startingNode, visited, path); // Perform DFS to generate strings

        StringBuilder result = new StringBuilder();
        int totalPaths = (int) Math.pow(k, n);

        // Build the final string by appending characters from the DFS path
        for (int i = 0; i < totalPaths; i++) {
            result.append(path.get(i));
        }

        result.append(startingNode); // Append the starting node
        return result.toString(); // Convert StringBuilder to String and return
    }

    // Recursive DFS function
    private static void dfs(int k, String current, Set<String> visited, List<Integer> path) {
        for (int i = 0; i < k; i++) {
            String next = current + (char) ('0' + i); // Append characters from 0 to k-1
            if (!visited.contains(next)) {
                visited.add(next); // Mark the string as visited
                dfs(k, next.substring(1), visited, path); // Recursively call DFS with the updated string
                path.add(i); // Add the current character to the path
            }
        }
    }
}