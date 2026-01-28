
/*Complete the function below
Node is as follows:
class Node{
int data;
Node left, right;
Node(int item)
{
    data = item;
    left = right = null
}
}
*/

class Solution{
    private static Map<Integer, Integer> mp;
    public ArrayList <Integer> verticalSum(Node root) {
        // add your code here
        ArrayList<Integer> ans = new ArrayList<>();
        mp = new LinkedHashMap<>();
        dfs(root, 0);

        mp = sortByKey(mp);

        for(Map.Entry<Integer, Integer> entry : mp.entrySet()) {
            ans.add(entry.getValue());
        }

        return ans;
    }
    private static void dfs(Node root, int level) {
        if(root == null)    return;
        mp.put(level, mp.getOrDefault(level, 0) + root.data);
        dfs(root.left, level - 1);
        dfs(root.right, level + 1);
    }


    public static Map<Integer, Integer>
    sortByKey(Map<Integer, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<Integer, Integer> > list
            = new LinkedList<Map.Entry<Integer, Integer> >(
                hm.entrySet());
 
        // Sort the list using lambda expression
        Collections.sort(
            list,
            (i1, i2) -> i1.getKey().compareTo(i2.getKey()));
 
        // put data from sorted list to hashmap
        HashMap<Integer, Integer> temp
            = new LinkedHashMap<Integer, Integer>();
        for (Map.Entry<Integer, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}

