


/*
class Node {
    int data;
    Node next;
    
    public Node (int data){
        this.data = data;
        this.next = null;
    }
}
*/
class Solution {    // reff

    public Node sort(Node head){
 
         if(head.next == null) 
 
         return head;
 
         ArrayList<Integer> a= new ArrayList<>() ;
 
         Node temp=head;
 
         //modifying the linked list and adding elements to arraylist
 
         while(temp!=null&&temp.next!=null) 
 
         {             
 
                  a.add(temp.next.data);
 
                  temp.next=temp.next.next;
 
                  temp=temp.next;
 
         }
 
         //reversing the array list
 
         Collections.reverse(a);
 
         //traversing to the end of linked list
 
         for(temp=head;temp.next!=null;temp=temp.next);
 
         //adding elements of array list to the linked list
 
         for(int I:a)
 
         {
 
             temp.next=new Node(I);
 
             temp=temp.next;
 
         }
 
         return head;
 
    }
 
 }