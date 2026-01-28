
class Solution{
    //Function to count the frequency of all elements from 1 to N in the array.
    public static void frequencyCount(int arr[], int N, int P)
    {
        // code here
        Map<Integer, Integer> m = new HashMap<>();
        for(int x :  arr) {
            if(!m.containsKey(x))
                m.put(x, 1);
            else m.put(x, m.get(x) + 1);
        }
        for(int i = 0; i < N; i++) {
            if(m.containsKey(i + 1))
                arr[i] = m.get(i + 1);
            else arr[i] = 0;
        }
    }
}
