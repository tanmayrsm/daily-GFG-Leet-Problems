class Solution
{
    public static Node modifyTheList(Node head)
    {
        if(head == null || head.next == null)
            return head;
        Node f = head;
        Node s = head;
        Node secHalfStart = null;
    
        int N = 1;
        
        while(f != null){
            f = f.next;
            if(f != null)
                N++;
            if(f != null) {
                f = f.next;
                if(f != null)
                    N++;
            }
            Node oldS = s;
            s = s.next;
            if(secHalfStart == null) {
                secHalfStart = oldS;
                secHalfStart.next = null;
            } else {
                Node temp = secHalfStart;
                secHalfStart = oldS;
                secHalfStart.next = temp;
            }
        }
        Node tracer = N % 2 == 1 ? secHalfStart.next : secHalfStart;    // traces first half backwards
        Node rem = null;
        while(s != null && tracer != null) {
            Node newer = new Node(s.data - tracer.data);
            if(rem == null) {
                rem = newer;
                rem.next = secHalfStart;
            } else {
                Node temp = rem;
                rem = newer;
                rem.next = temp;
            }
            tracer = tracer.next;
            s = s.next;
        }
        return rem;
    }
}