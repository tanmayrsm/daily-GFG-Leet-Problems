class Solution {
    public static ArrayList<ArrayList<Integer>> uniquePerms(int[] arr) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        Arrays.sort(arr);
        boolean[] used = new boolean[arr.length];
        backtrack(arr,used, new ArrayList<>(),res);
        return res;
    }

    private static void backtrack(int[] arr, boolean[] used, List<Integer> path, ArrayList<ArrayList<Integer>> res){
        if(path.size() == arr.length){
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i=0; i<arr.length; i++){
            if(used[i]) continue;

            if(i>0 && arr[i] == arr[i-1] && !used[i-1]) continue;

            used[i] = true;
            path.add(arr[i]);

            backtrack(arr,used,path,res);

            path.remove(path.size() - 1);
            used[i] = false;
        }
    }
};