class Solution
{
    private static TreeMap<Integer, Boolean> primeListNos = new TreeMap<>();
    private static void makeMap() {
        int n = 20000;
        // Create a boolean array "prime[0..n]" and initialize
        // all entries it as true. A value in prime[i] will
        // finally be false if i is Not a prime, else true.
        boolean prime[] = new boolean[n+1];
        for(int i=0;i<=n;i++)
            prime[i] = true;
         
        for(int p = 2; p*p <=n; p++)
        {
            // If prime[p] is not changed, then it is a prime
            if(prime[p] == true)
            {
                // Update all multiples of p
                for(int i = p*p; i <= n; i += p)
                    prime[i] = false;
            }
        }
         
        // Print all prime numbers
        for(int i = 2; i <= n; i++)
        {
            if(prime[i] == true)
                primeListNos.put(i, true);
        }
    }
    Node primeList(Node head){
        //code here
        Node root = head;
        if(primeListNos.size() == 0)
            makeMap();
        
        while(head != null){
            int data = head.val;
            if(primeListNos.get(data) == null) {
                var ceilKey = primeListNos.ceilingEntry(data);
                var floorKey = primeListNos.floorEntry(data);
                if(ceilKey != null && floorKey != null && ceilKey.getKey() - data == data -floorKey.getKey()) {
                    head.val = floorKey.getKey();
                } else if(floorKey != null && ceilKey != null) {
                    head.val = (data - floorKey.getKey()) < ceilKey.getKey() - data ? floorKey.getKey() : ceilKey.getKey();
                } else if(floorKey == null && ceilKey != null){
                    head.val = ceilKey.getKey();
                } else if(floorKey != null && ceilKey == null){
                    head.val = floorKey.getKey();
                }
            }
            head = head.next;
        }
        
        return root;
    }
}