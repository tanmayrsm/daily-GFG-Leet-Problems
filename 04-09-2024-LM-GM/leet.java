class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        int x = 0, y = 0, ans = 0;
        Set<List<Integer>> obs = new HashSet<>();
        String curDir = "UP";
        for (int[] obstacle : obstacles) 
            obs.add(Arrays.asList(obstacle[0], obstacle[1]));
        for (int command : commands) {
            if (command == -1) {
                switch (curDir) {
                    case "UP":
                        curDir = "RIGHT";
                        break;
                    case "RIGHT":
                        curDir = "DOWN";
                        break;
                    case "DOWN":
                        curDir = "LEFT";
                        break;
                    default:
                        curDir = "UP";
                        break;
                }
            } else if (command == -2) {
                switch (curDir) {
                    case "UP":
                        curDir = "LEFT";
                        break;
                    case "LEFT":
                        curDir = "DOWN";
                        break;
                    case "DOWN":
                        curDir = "RIGHT";
                        break;
                    default:
                        curDir = "UP";
                        break;
                }
            } else {
                switch (curDir) {
                    case "UP":
                        while (command > 0 && !obs.contains(Arrays.asList(x, y+1))) {
                            y++;
                            command--;
                        }
                        break;
                    case "LEFT":
                        while (command > 0 && !obs.contains(Arrays.asList(x-1, y))) {
                            x--;
                            command--;
                        }
                        break;
                    case "DOWN":
                        while (command > 0 && !obs.contains(Arrays.asList(x, y-1))) {
                            y--;
                            command--;
                        }
                        break;
                    default:
                        while (command > 0 && !obs.contains(Arrays.asList(x+1, y))) {
                            x++;
                            command--;
                        }
                        break;
                }
            }
            ans = Math.max(ans, x * x + y * y);
        }
        return ans;
    }
}
// -2 => left by 90 deg
// -1 => turn right by 90 deg
// else go forward in that dir