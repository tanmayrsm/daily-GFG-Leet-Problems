
class Solution {
    public ArrayList<ArrayList<Integer>> fourSum(int[] arr, int k) {
        // code here
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

                    // Sort the input array for efficient two-pointer approach
                    Arrays.sort(arr);
                
                    for (int i = 0; i < arr.length - 3; i++) {
                        for (int j = i + 1; j < arr.length - 2; j++) {
                            int left = j + 1;
                            int right = arr.length - 1;
                
                            while (left < right) {
                                int sum = arr[i] + arr[j] + arr[left] + arr[right];
                
                                if (sum == k) {
                                    ArrayList<Integer> quadruplet = new ArrayList<>();
                                    quadruplet.add(arr[i]);
                                    quadruplet.add(arr[j]);
                                    quadruplet.add(arr[left]);
                                    quadruplet.add(arr[right]);
                                    result.add(quadruplet);
                
                                    // Move the left and right pointers
                                    left++;
                                    right--;
                
                                    // Avoid duplicate solutions
                                    while (left < right && arr[left] == arr[left - 1]) {
                                        left++;
                                    }
                                    while (left < right && arr[right] == arr[right + 1]) {
                                        right--;
                                    }
                                } else if (sum < k) {
                                    left++;
                                } else {
                                    right--;
                                }
                            }
                
                            // Avoid duplicate pairs
                            while (j < arr.length - 2 && arr[j] == arr[j + 1]) {
                                j++;
                            }
                        }
                
                        // Avoid duplicate elements
                        while (i < arr.length - 3 && arr[i] == arr[i + 1]) {
                            i++;
                        }
                    }
                
                    return result;
            }
}