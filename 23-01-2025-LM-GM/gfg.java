class Solution {
    public Node cloneLinkedList(Node head) {
        // code here
        // soln 1 -  two pass (first pass, assign all next, in next pass, assign random), it will still need map for second pass
        // soln2  - one pass - map
        Map<Node, Node> mp = new HashMap<>();
        Node ansHead = null, current = null;
        while(head != null) {
            Node temp = head;
            head = head.next;
            Node currNewHead = mp.containsKey(temp) ? mp.get(temp) : new Node(temp.data);
            mp.put(temp, currNewHead);
            if (ansHead == null) {
                ansHead = currNewHead;
                current = ansHead;
            } else {
                current.next = currNewHead;
                current = current.next;
            }
            Node random = temp.random;
            if(random != null) {
                Node newRandom = mp.containsKey(random) ? mp.get(random) : new Node(random.data);
                mp.put(random, newRandom);
                currNewHead.random = newRandom;
            }
        }
        return ansHead;
        
    }
}