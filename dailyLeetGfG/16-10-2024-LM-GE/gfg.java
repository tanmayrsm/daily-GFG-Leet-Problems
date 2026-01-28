
class Solution {

    public boolean checkSorted(List<Integer> arr) {
        // code here
        int n = arr.size();
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            if(arr.get(i) != i + 1) {
                list.add(i);
            }
        }
        int m = list.size();
        if(m == 0)  return true;
        if(m > 4 || m <= 2) return false;
        if(m == 3)  return true;
        for(int i = 1; i < 4; i++)
            if(arr.get(list.get(i - 1)) < arr.get(list.get(i)))
                return false;
        return true;
    }
}