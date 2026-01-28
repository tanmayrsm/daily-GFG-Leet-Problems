
class Solution {
    
    public void Rearrange(int a[], int n)
    {
        // Your code goes here
        // merge sort
        mergesort(a, 0, n - 1);
    }
    private static void mergesort(int[] a, int l, int r) {
        if(l < r) {
            int mid = l + (r - l) / 2;
            mergesort(a, l, mid);
            mergesort(a, mid + 1, r);
            merge(a, l, mid, r);
        }
    }
    private static void merge(int[] a, int l, int mid, int r) {
        int s1 = mid - l + 1;
        int s2 = r - mid;
        int[] a1 = new int[s1];
        int[] a2 = new int[s2];
        int p = l;
        for(int i = 0; i < s1; i++)
            a1[i] = a[p++];
        for(int i = 0; i < s2; i++)
            a2[i] = a[p++];
        int a1Ptr = 0;
        int a2Ptr = 0;
        p = l;
        while(a1Ptr < s1 && a2Ptr < s2) {
            if(a1[a1Ptr] < 0)       // change in MS algo - 1
                a[p++] = a1[a1Ptr++];
            else if (a2[a2Ptr] <0)    a[p++] = a2[a2Ptr++]; // change in MS algo - 2
            else    break;  // change in MS algo - 3
        }
        while(a1Ptr < s1)
            a[p++] = a1[a1Ptr++];
        while(a2Ptr < s2)
            a[p++] = a2[a2Ptr++];
    }
}