
class Solution
{
    //Function to build a Heap from array.
    void buildHeap(int arr[], int n)
    {
        // Your code here
        for(int i = (n - 2) / 2; i >= 0; i--)
            heapify(arr, n, i);
    }
 
    //Heapify function to maintain heap property.
    void heapify(int arr[], int n, int i)   // i is root node
    {
        // Your code here
        int largest = i;
        int l = 2*i + 1, r = 2*i + 2;    // getting left and right nodes
        // find index of max element among root, left and right nodes
        if(l < n && arr[l] > arr[largest])
            largest = l;
        if(r < n && arr[r] > arr[largest])
            largest = r;
        // largest element is NOT ROOT, so call heapify again
        if(i != largest) {
            swap(arr, i, largest);  // swap largest and place it at roots pos
            heapify(arr, n, largest);
        }
    }
    
    //Function to sort an array using Heap Sort.
    public void heapSort(int arr[], int n)
    {
        //code here
        buildHeap(arr, n);
        for(int i = n - 1; i >= 1; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
 }
 