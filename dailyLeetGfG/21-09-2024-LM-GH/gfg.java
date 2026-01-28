
/*Please note that it's Function problem i.e.
you need to write your solution in the form of Function(s) only.
Driver Code to call/invoke your function is mentioned above.*/

/*
class Node {
    int data;
    Node next, random;

    Node(int d)
    {
        data = d;
        next = random = null;

    }
}*/

class Solution {
    // Function to clone a linked list with next and random pointer.
    Node copyList(Node head) {
        // your code here
        Node ansHead = null, prev = null;
        Map<Integer, Node> mp = new HashMap<>();
        while(head != null) {
            Node curr = null, temp = head;
            head = head.next;
            if(mp.containsKey(temp.data)) {
                curr = mp.get(temp.data);
            } else {
                curr = new Node(temp.data);
                mp.put(temp.data, curr);
            }

            // set next pointers
            if (prev == null) {
                ansHead = curr;
                prev = curr;
            } else {
                prev.next = curr;
                prev = prev.next;
            }
            
            // set random pointers
            Node rand = temp.random;
            if(rand != null) {
                if(!mp.containsKey(rand.data)) {
                    Node newRand = new Node(rand.data);
                    mp.put(rand.data, newRand);   
                }
                prev.random = mp.get(rand.data);
            }
        }
        return ans;
    }
}