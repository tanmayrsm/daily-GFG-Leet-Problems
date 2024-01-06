class Solution {
    private class Job {
        private int start;
        private int end;
        private int profit;

        public Job(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        // the concept lies with 
        // Profit at endTime x = Profit till endTime x + currentProfit(from startTime to endTime x)
        // we use treeMap (which usese BST)
        // as using floorKey property, its easy to get key less that endTime x directly
        List<Job> jobs = new ArrayList<>();
        for(int i = 0; i < startTime.length; i++) {
            jobs.add(new Job(startTime[i], endTime[i], profit[i]));
        }
        // sorting as per endTime, as our whole base is based on endTime
        Collections.sort(jobs, (a,b) -> (a.end - b.end));

        TreeMap<Integer, Integer> map = new TreeMap<>();
        int ans = 0;

        for(Job currJob : jobs) {
            Integer entryTillStartTime = map.floorKey(currJob.start);   // check if map has any key lower than currJob start
            int maxProfitTillStartTime = 0;
            if(entryTillStartTime != null) {
                maxProfitTillStartTime = map.get(entryTillStartTime);
            }

            ans = Math.max(ans, maxProfitTillStartTime + currJob.profit);
            map.put(currJob.end, ans);
        }
        return ans;
    }















}