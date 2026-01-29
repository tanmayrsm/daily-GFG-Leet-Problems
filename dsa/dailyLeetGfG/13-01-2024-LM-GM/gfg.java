
//User function Template for Java

/*class Node
    {
        int data;
        Node next;
        Node(int d) {data = d; next = null; }
    }
    */
    class Solution
    {
        public static Node insertionSort(Node head) {
            //code here
            Node start = head;
            Node headNext = head.next;
            Node s = start;
            while(headNext != null) {
                s = start;
                Node currNext = headNext;
                while(s != headNext) {
                    if(currNext.data < s.data) {
                        int temp = s.data;
                        s.data = currNext.data;
                        currNext.data = temp;
                    }
                    s = s.next;
                }
                headNext = headNext.next;
            }
            return start;
            
        }
    }