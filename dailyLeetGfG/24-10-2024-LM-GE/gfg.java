class Solution {
    static ArrayList<Integer> modifyAndRearrangeArr(int arr[]) {
        // Complete the function
        int n = arr.length, zeroCt = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 1; i < n; i++) {
            if(arr[i] != 0 && arr[i] == arr[i - 1]) {
                arr[i - 1] *= 2;
                arr[i] = 0;
            }
            if(arr[i - 1] > 0)
                ans.add(arr[i - 1]);
            if(arr[i - 1] == 0)
                zeroCt++;
        }
        if(arr[n - 1] > 0)  ans.add(arr[n - 1]);
        else zeroCt++;
        for(int j = 0; j < zeroCt; j++)
            ans.add(0);
        return ans;
    }
}
