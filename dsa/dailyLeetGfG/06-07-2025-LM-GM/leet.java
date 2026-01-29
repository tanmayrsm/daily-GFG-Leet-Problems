import java.util.HashMap;
import java.util.Map;

class FindSumPairs {
    Map<Integer, Integer> mp1, mp2;
    int[] nums2;
    public FindSumPairs(int[] nums1, int[] nums2) {
        mp1 = new HashMap<>();
        mp2 = new HashMap<>();
        for (int x : nums1) {
            mp1.put(x, mp1.getOrDefault(x, 0) + 1);
        }
        for (int x : nums2) {
            mp2.put(x, mp2.getOrDefault(x, 0) + 1);
        }
        this.nums2 = nums2;
    }
    
    public void add(int index, int val) {
        int prevNo = nums2[index];
        if (mp2.get(prevNo) == 1)   mp2.remove(prevNo);
        else mp2.put(prevNo, mp2.get(prevNo) - 1);
        nums2[index] += val;
        mp2.put(nums2[index], mp2.getOrDefault(nums2[index], 0) + 1);    
    }
    
    public int count(int tot) { // at max, this func would be called 1k times
        int ans = 0;
        for (Map.Entry<Integer, Integer> x : mp1.entrySet()) {  // at max mp1 has size 1k
            int rem = tot - x.getKey();
            if (rem >= 0 && mp2.containsKey(rem)) {
                ans += mp2.get(rem) * x.getValue();
            }
        }
        return ans;
    }
}

/**
 * Your FindSumPairs object will be instantiated and called as such:
 * FindSumPairs obj = new FindSumPairs(nums1, nums2);
 * obj.add(index,val);
 * int param_2 = obj.count(tot);

FindSumPairs findSumPairs = new FindSumPairs([1, 1, 2, 2, 2, 3], [1, 4, 5, 2, 5, 4]);
findSumPairs.count(7);  // return 8; pairs (2,2), (3,2), (4,2), (2,4), (3,4), (4,4) make 2 + 5 and pairs (5,1), (5,5) make 3 + 4
findSumPairs.add(3, 2); // now nums2 = [1,4,5,4,5,4]
findSumPairs.count(8);  // return 2; pairs (5,2), (5,4) make 3 + 5
findSumPairs.count(4);  // return 1; pair (5,0) makes 3 + 1
findSumPairs.add(0, 1); // now nums2 = [2,4,5,4,5,4]
findSumPairs.add(1, 1); // now nums2 = [2,5,5,4,5,4]
findSumPairs.count(7);  // return 11; pairs (2,1), (2,2), (2,4), (3,1), (3,2), (3,4), (4,1), (4,2), (4,4) make 2 + 5 and pairs (5,3), (5,5) make 3 + 4


[1,1,2,2,2,3]
[2,4,4,5,5,5]

count -> 7


 */
