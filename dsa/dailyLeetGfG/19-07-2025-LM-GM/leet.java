import java.util.*;
class Solution {
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder);
        Set<String>set=new HashSet<>();
        List<String>list=new ArrayList<>();

        for(String s:folder){
            StringTokenizer str=new StringTokenizer(s,"/");
            boolean present=false;
            StringBuilder cur=new StringBuilder();

            while(str.hasMoreTokens()){
                cur.append("/");cur.append(str.nextToken());
                if(set.contains(cur.toString())){
                    present=true;
                    break;
                }
            }

            if(!present){
                set.add(s);list.add(s);
            }

        }
        return list;
    }
}