
class Solution {
    HashMap<String,Integer>hm = new HashMap<>();
  int dupSub(Node root) {
      // code here 
         solve(root);
      for(String a:hm.keySet())
      {
          if(hm.get(a)>1)
          return 1;
      }
      return 0;

  }
  String solve(Node root)
  {
      Node curr=root;
      if(curr==null)
      return "";
      String ans="";
      String l=solve(curr.left);
      String r=solve(curr.right);
      ans=l+curr.data+r;
      if(curr.left!=null || curr.right!=null)
          hm.put(ans,hm.getOrDefault(ans,0)+1);
      return ans;
  }

}