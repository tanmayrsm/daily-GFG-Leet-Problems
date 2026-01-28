class Solution {
    public List<List<Integer>> findWinners(int[][] matches) {
       List<List<Integer>> ans = new ArrayList<>();
       Set<Integer> allPlayers = new HashSet<>();
       Map<Integer, Integer> playerWithLoss = new HashMap<>();
       
       for(int[] match : matches) {
           allPlayers.add(match[0]);
           allPlayers.add(match[1]);
           playerWithLoss.put(match[1], playerWithLoss.getOrDefault(match[1], 0) + 1);
       }

       // player who never lost
       List<Integer> neverLost = new ArrayList<>();
       for(Integer player : allPlayers) {
            if(!playerWithLoss.containsKey(player))
                neverLost.add(player);
       }

       // player with have just one loss
       List<Integer> lostOnlyOnce = new ArrayList<>();
       for(Map.Entry<Integer, Integer> player : playerWithLoss.entrySet()) {
            if(player.getValue() == 1)
                lostOnlyOnce.add(player.getKey());
       }

       Collections.sort(neverLost);
       Collections.sort(lostOnlyOnce);
       ans.add(neverLost);
       ans.add(lostOnlyOnce);
       return ans;
         
    }
}