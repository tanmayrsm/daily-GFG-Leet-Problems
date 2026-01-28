class Geeks{
    
    // Function to insert element into the queue
    static Map<Integer, Integer> m;
    static void insert(Queue<Integer> q, int k){
        // Your code here
        if(q.size() == 0)
            m = new HashMap<>();
        if(m.get(k) == null)
            m.put(k, 1);
        else m.put(k, m.get(k) + 1);
        q.offer(k);
    }
    
    // Function to find frequency of an element
    // rteturn the frequency of k
    static int findFrequency(Queue<Integer> q, int k){
        
        // Your code here
        if(m.get(k) == null)
            return -1;
        return m.get(k);
        
    }
    
}