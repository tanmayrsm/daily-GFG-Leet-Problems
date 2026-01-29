class Solution {
    class Robot {
        int pos, l, r;
        Robot(int pos, int l, int r) {
            this.pos = pos;
            this.l = l;
            this.r = r;
        }
    }
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        // step1 : for each robot, recalculate its range
        // step2 : for each robot range, fin (x,y) index in distance with BS that it covers, and append in some list
        // step3 : sort intervals, and find gaps between two of intervals, and find how many walls between that range
        // fin (x,y)
        List<Robot> robotsL = new ArrayList<>();
        int n = robots.length;
        Arrays.sort(walls);
        for (int i = 0; i < n; i++) {
            robotsL.add(new Robot(robots[i], distance[i], distance[i]));
        }

        // step1 :
        Collections.sort(robotsL, (Robot a, Robot b) -> Integer.compare(a.pos, b.pos));
        for (int i = 0; i < n; i++) {
            int left = distance[i], right = distance[i];
            if (i >= 1 && (Math.abs(robotsL.get(i).pos - robotsL.get(i - 1).pos) < distance[i]))
                left = Math.abs(robotsL.get(i).pos - robotsL.get(i - 1).pos);
            if (i < n - 1 && (Math.abs(robotsL.get(i + 1).pos - robotsL.get(i).pos) < distance[i]))
                right = Math.abs(robotsL.get(i + 1).pos - robotsL.get(i).pos);
            robotsL.get(i).l = left;
            robotsL.get(i).r = right;
        }

        // step2:
        List<int[]> wallsInRange = new ArrayList<>();
        for (Robot r : robotsL) {
            int[] wallInRange = fin(r.l, r.r, walls);
            if (wallInRange[0] != -1) {
                wallsInRange.append(wallInRange);
            }
        }

        // step3:
        int k = wallsInRange.size(), wallsOutOfRange = 0;
        for (int i = 1; i < k; i++) {
            int curr = wallsInRange[i], prev = wallsInRange[i - 1];
            if (curr[0] > prev[1]) {    // wall range gap found
                int[] wallOutOfRangeL = fin(prev[1]+1, curr[0]-1, walls);
                if (wallOutOfRangeL[0] != -1) {
                    wallOutOfRange += wallOutOfRangeL[1] - wallsOutOfRange[0];
                }
            }
        }

        return walls.length - wallsOutOfRange;
    }

    private int[] fin(int x, int y, int[] walls) {
        int n = walls.length, l = 0, r = n - 1, mid = (l + r) / 2, left = -1, right = -1;
        if (walls[0] > y || walls[n - 1] < x)   return new int[] {-1, -1};

        // find left bound
        while (l <= r) {
            mid = (l + r) / 2;
            if (walls[mid] == x)
                left = mid;
            if (walls[mid] >= x) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        l = 0; r = n - 1;
        // find right bound
        while (l <= r) {
            mid = (l + r) / 2;
            if (walls[mid] == y)
                right = mid;
            if (walls[mid] > y) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return new int[] {(left == -1) ? 0 : left, (right == -1) ? n - 1 : right};

    }
//    [4,8,90], (2,5) -> [0,0]
//            (2,500) -> [0, 2]
//            (0,3)   -> [-1, -1]
//            (300,500)> [-1, -1]
}