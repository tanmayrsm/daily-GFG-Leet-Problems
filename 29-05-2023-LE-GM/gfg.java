
class Solution{
    ArrayList<String> ans;
    ArrayList<String> CamelCase(int N,String[] Dictionary,String Pattern){
        //code here
        // make a trie with Uppercase letters from each word in dict
        // at the end of each word, insert that word in a list for that trie
        // eg - WTG - [WhatTheGood, WhatTheGall]
        // AB - [AnnaBoye, AyaBol]
        ans = new ArrayList<>();
        
        trie root=new trie();
        build(Dictionary,root);
        find(Pattern,root);

        //asked by question to reurn in lexo order
        Collections.sort(ans);
        if(ans.isEmpty()) ans.add("-1");
        return ans;
    }
    
    private void build(String[] a, trie root) {
        trie temp = null;
        for(int i = 0; i < a.length; i++) {
            temp = root;
            for(int j = 0; j < a[i].length(); j++) {
                char curr = a[i].charAt(j);
                if(Character.isUpperCase(curr)) {
                    if(temp.ch[curr - 'A'] == null) {
                        temp.ch[curr - 'A'] = new trie();
                    }
                    temp = temp.ch[curr - 'A'];
                }
            }
            temp.al.add(a[i]);
        }
    }
    
    private int find(String s, trie root) {
        for(int i = 0; i < s.length(); i++) {
            if(root.ch[s.charAt(i) - 'A'] == null)
                return 0;
            root = root.ch[s.charAt(i) - 'A'];
        }
        printAllWords(root);
        return 1;
    }
    
    private void printAllWords(trie root) {
        //add all the strings ass and do the same for its child.
        for (String str : root.al) 
            ans.add(str);
        for (int i = 0; i < 26; i++) {
            trie child = root.ch[i];
            if (child != null) 
                printAllWords(child);
        }
    }
    
    public class trie
    {
        trie ch[];
        ArrayList<String> al ;
        public trie()
        {
            ch=new trie[26];
            for(int i=0;i<26;i++){
                ch[i]=null;
            }
            al =new ArrayList<>();
        }
    }
}