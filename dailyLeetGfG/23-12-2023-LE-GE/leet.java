class Solution {
    public boolean isPathCrossing(String path) {
        int x = 0, y = 0;
        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        visited.add(new Pair<>(0, 0));

        for (char direction : path.toCharArray()) {
            if (direction == 'N') y++;
            else if (direction == 'S') y--;
            else if (direction == 'E') x++;
            else x--;

            Pair<Integer, Integer> currentPos = new Pair<>(x, y);
            if (visited.contains(currentPos)) {
                return true; // Path crosses itself
            } else {
                visited.add(currentPos);
            }
        }

        return false; // Path does not cross itself
    }
}