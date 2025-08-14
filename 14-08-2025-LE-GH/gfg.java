class Solution {
        public int countRevPairs(int[] arr) {
                if (arr == null || arr.length < 2) return 0;
                return mergeSort(arr, 0, arr.length - 1);
        }

        private int mergeSort(int[] arr, int low, int high) {
                if (low >= high) return 0;
                int mid = (low + high) >>> 1;
                int count = mergeSort(arr, low, mid)
                        + mergeSort(arr, mid + 1, high)
                        + mergeAndCount(arr, low, mid, high);
                return count;
        }

        private int mergeAndCount(int[] arr, int low, int mid, int high) {
                int count = 0;
                int j = mid + 1;

                // Count reverse pairs across the halves
                for (int i = low; i <= mid; i++) {
                        while (j <= high && (long) arr[i] > 2L * arr[j]) j++;
                        count += j - (mid + 1);
                }

                // Merge the two sorted halves
                int[] tmp = new int[high - low + 1];
                int left = low, right = mid + 1, k = 0;

                while (left <= mid && right <= high) {
                        if (arr[left] <= arr[right]) tmp[k++] = arr[left++];
                        else tmp[k++] = arr[right++];
                }
                while (left <= mid) tmp[k++] = arr[left++];
                while (right <= high) tmp[k++] = arr[right++];

                // Copy back
                for (int i = 0; i < tmp.length; i++) arr[low + i] = tmp[i];

                return count;
        }
}