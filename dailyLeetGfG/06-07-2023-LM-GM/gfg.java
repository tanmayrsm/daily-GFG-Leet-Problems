
class Solution
{
    //Function to sort an array using quick sort algorithm.
    static void quickSort(int arr[], int low, int high)
    {
        // code here
        if(low < high) {
            int part = partition(arr, low, high);
            quickSort(arr, low, part - 1);
            quickSort(arr, part + 1, high);
        }
    }
    static int partition(int arr[], int low, int high)
    {
        // your code here
        int pivot = low;
        while(low < high) {
            while(low < high && arr[low] <= arr[pivot])
                low++;
            while(high >= 0 && arr[high] > arr[pivot])
                high--;
            if(low < high) {    // if low and high havent crossed, swap them
                int temp = arr[low];
                arr[low] = arr[high];
                arr[high] = temp;
            }
        }
        // finally swap pivot and high
        int temp = arr[pivot];
        arr[pivot] = arr[high];
        arr[high] = temp;
        return high;
    } 
}