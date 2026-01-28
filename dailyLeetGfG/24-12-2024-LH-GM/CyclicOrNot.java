// linkedlist -> say if its cycklic or not

//
class Node {
    int data;
    Node next;
    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class CyclicOrNot {
    public static boolean isLinkedListCyclic(Node head) {
        Node fast = head, slow = head;
        while(fast != null) {
            fast = fast.next;   // 1st jump
            if(fast == null)
                return false;
            fast = fast.next;   // 2nd jump 
            slow = slow.next;   // one jump
            if(slow == fast)
                return true;
        }
        return false;
    }
    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        // n4.next = n2;

        System.out.println("1 output :: " + isLinkedListCyclic(n1));
        
        System.out.println("2 output :: " + isLinkedListCyclic(null));
        
    }
    
}

// set<Nodes> -> visited or not
// 1 -> 2 -> 3
//      |    |
//       ---4

// 1 , 2, 3, 4, -> 2 already in set -> true,
// encounter null -> false

// O(n) - TC, SC - O(n)
// two pointer -> pointing at 1
// fast -> next 2 steps, slow -> next step
// cycle -> fast and slow collide
// SC - O(1)

// set -> Object
// map -> key : value
