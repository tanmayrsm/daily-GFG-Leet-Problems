class Solution{
    //Function to add two numbers represented by linked list.
    static Node addTwoLists(Node l1, Node l2){
        // code here
        // return head of sum list
        Stack<Integer> s1=new Stack<Integer>();
        Stack<Integer> s2=new Stack<Integer>();
        Node res=null;
        while (l1 != null)
        {
            s1.push(l1.data);
            l1=l1.next;
        }
        while (l2 !=null)
        {
            s2.push(l2.data);
            l2=l2.next;
        }
        int c=0;
        while (!s1.isEmpty() || !s2.isEmpty())
        {     
            int a=0;
            int b=0;  
            if (!s1.isEmpty())
            {
                a=s1.pop();
            }
            if (!s2.isEmpty())
            {
                b=s2.pop();
            }
            int t=a+b+c;
            Node tmp=new Node(t%10);
            c=t /10;
            if (res==null)
            {
                res=tmp;
            }
            else
            {
                tmp.next=res;
                res=tmp;
            }
        }
        if(c!=0)
        {
            Node tmp=new Node(c);
            tmp.next=res;
            res=tmp;
        }
        while (res!=null && res.data==0)
        {
            res=res.next;
        }
        if (res!=null)
        {
            return res;
        }
        Node ans=new Node(0);
        return ans;
    }
}