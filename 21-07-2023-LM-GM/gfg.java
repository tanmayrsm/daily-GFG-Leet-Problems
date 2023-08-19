
class Solution
{
    public static Node reverse(Node node, int k) {
        //Your code here
        Node first = node;
        
        Node temp = node;
        Node start = null;
        int ct = 0;
        Node startOfPrev = null;
        Node startOfPrev2 = null;
        Node ans = null;
        Node oldTemp = null;
        Node oldTempCopy = null;
        while(temp != null) {
            oldTemp = temp;
            temp = temp.next;
            if(start == null) {
                start = oldTemp;
                if(startOfPrev == null)
                    startOfPrev = start;
                else if (startOfPrev2 == null) {
                    // System.out.println("sop2 :: " + start.data);
                    startOfPrev2 = start;
                }
                start.next = null;
            } else {
                oldTemp.next = start;
                start = oldTemp;
                // System.out.println("start :: " + start.data);
            }
            ct++;
            if(ct == k || temp == null) {
                if(ans == null)
                    ans = oldTemp;
                ct = 0;
                if(startOfPrev2 != null) {
                    // System.out.println("sop2 :: " + startOfPrev2.data);
                    startOfPrev.next = oldTemp;
                    startOfPrev = startOfPrev2;
                    startOfPrev2 = null;
                }
                start = null;
            }
        }
        return ans;

        //  1->2->2->4->5->6->7->8 
        //  4->2->2->1->8->7->6->5 
        //  4->2->2->1  5->6->7->8
        

        
    }
}