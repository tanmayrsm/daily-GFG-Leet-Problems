class Solution {
    int getSingle(int arr[]) {
        // code here
        int no = 0;
        for(int num : arr)
            no ^= num;
        return no;
    }
}