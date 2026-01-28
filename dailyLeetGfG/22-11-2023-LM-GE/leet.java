import java.util.HashMap;
import java.util.List;

class Solution {
    static int[] ans;
    static LinkedHashMap<Integer, List<Integer>> mp;
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        mp = new LinkedHashMap<>();
        int totalElems = 0, idx = 0;
        for(int i = 0; i < nums.size(); i++) {
            for(int j = 0; j < nums.get(i).size(); j++) {
                if(mp.get(i + j) == null)
                    mp.put(i + j, new ArrayList<>());
                mp.get(i + j).add(nums.get(i).get(j));
                totalElems++;
            }
        }
        ans = new int[totalElems];
        for(Map.Entry<Integer, List<Integer>> x : mp.entrySet()) {
            List<Integer> val = x.getValue();
            for(int i = val.size() - 1; i >= 0; i--)
                ans[idx++] = val.get(i);
        }
        return ans;
    }
}