class Solution {
    public List<Integer> rearrange(List<Integer> arr) {
        // Code here
        int n = arr.size();
        for(int i = 0; i < n; i++) {
            if(arr.get(i) != -1) {
                int elem = arr.get(i);
                int next = arr.get(elem);
                // elem = next;
                int idx = i;
                while(elem != -1 && arr.get(elem) != elem) {
                    next = arr.get(elem);
                    arr.set(elem, elem);
                    elem = next;
                }
            }
            if(arr.get(i) != i)
                arr.set(i, -1);
        }
        return arr;
    }
}