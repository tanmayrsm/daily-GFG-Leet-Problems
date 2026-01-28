import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

class rem implements Comparator<Integer> {
    public int compare(int a, int b) {
        return a > b;
    }
}
class Solution {
    //Function to find maximum of each subarray of size k.
    static ArrayList <Integer> max_of_subarrays(int arr[], int n, int k)
    {
        // Your code here
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        ArrayList<Integer> ans = new ArrayList<>();
        
        for(int i = 0; i < k; i++)
            q.offer(arr[i]);
        ans.add(q.peek());
        for(int i = 1; i + k < n; i++) {
            int end = i + k;
            if(q.isEmpty())
                q.offer(arr[end]);
            else {

            }
        }

        return ans;
    }
}