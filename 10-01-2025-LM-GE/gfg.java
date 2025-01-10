class Solution {
    ArrayList<Integer> countDistinct(int arr[], int k) {
        // code here
        ArrayList<Integer> list = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        int j = 0;

        for (int i = 0; i < arr.length; i++) {

            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);

            if (i - j + 1 >= k) {
                list.add(map.size());

                if (map.get(arr[j]) > 1)
                    map.put(arr[j], map.get(arr[j]) - 1);
                else
                    map.remove(arr[j]);

                j++;
            }

        }

        return list;
    }
}