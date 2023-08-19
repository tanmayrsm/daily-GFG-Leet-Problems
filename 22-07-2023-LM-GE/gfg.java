
class Solution
{
    //Function to remove duplicates from unsorted linked list.
    public Node removeDuplicates(Node head) 
    {
         // Your code here
         Set<Integer> s = new HashSet<>();
         Node ans = head;
         Node ans2 = ans;
         s.add(head.data);
         head = head.next;
         while(head != null) {
            if(s.contains(head.data)) {
                ans.next = null;
                head = head.next;
            } else {
                ans.next = head;
                s.add(head.data);
                head = head.next;
                ans = ans.next;
            }
         }
         return ans2;
    }
}