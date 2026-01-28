
// class Node {
//     int data;
//     Node npx;

//     Node(int x) {
//         data = x;
//         npx = null;
//     }
// }
class Solution {
    // function should insert the data to the front of the list
    static Node insert(Node head, int data) {
        Node t1=new Node(data);
        t1.npx=head;
        return t1;
    }

    // function to print the linked list
    static ArrayList<Integer> getList(Node head) {
        ArrayList<Integer> arr=new ArrayList<>();
        while(head!=null){
            arr.add(head.data);
            head=head.npx;
        }
        return arr;
    }
}