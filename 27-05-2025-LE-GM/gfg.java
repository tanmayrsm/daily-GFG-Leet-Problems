class Solution {
    public ArrayList<Integer> leafNodes(int[] p) {
        // code here
        ArrayList<Integer>arr=new ArrayList<Integer>();
        Stack<Integer>st=new Stack<>();
        boolean isLeaf=false;
        int n=p.length;
        int curr,next;
        for(int i=0;i<n-1;i++)
        {
            curr=p[i];
            next=p[i+1];
            if(next<curr)
            {
                //going left sub tree
                st.push(curr);
            }
            else
            {
                //going right subtree
                while(!st.isEmpty() && next>st.peek())
                {
                    st.pop();
                    isLeaf=true;
                }
            }
            if(isLeaf)
            {
                arr.add(curr);
            }
            isLeaf=false;
        }
        //last node always the leaf node
        arr.add(p[n-1]);
        return arr;
    }
}