
//Function to check whether a Binary Tree is BST or not.
public static void findPreSuc(Node root, int key)
{
    // code here.
    
    /* There are two static nodes defined above pre(representing predecessor) and suc(representing successor) as follows:
       static Node pre=null,suc=null
       You need to update these both.
       And the data inside these classes will be printed automatically by the driver code. 
    */
    suc = findImmediateBig(root, key);
    pre = findImmediateSmall(root, key);
    
}
static Node findImmediateBig(Node root, int x)
{
    Node res =null;
    while(root!=null)
    {
        if(root.data>x)
        {
            res=root;
            root=root.left;
        }
        else root=root.right;
    }
    return res;
}

static Node findImmediateSmall(Node root, int x)
{
    Node res =null;
    while(root!=null)
    {
        if(root.data>=x)
        {

          root=root.left;
        }
        else
        {  res=root;
            root=root.right;
        }
    }
    return res;
}
