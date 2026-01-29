class Solution {
    
    public Node arrangeCV(Node head){
        //write code here and return the head of changed linked list
        Node vowel = null, vH = null, conso = null, cH = null;
        Set<Character> v = Set.of('a', 'e', 'i', 'o', 'u');
        while(head != null) {
            Node temp = head;
            head = head.next;
            if(v.contains(temp.data)) {
                if(vH == null) {
                    vH = temp;
                    vowel = vH;
                } else {
                    vowel.next = temp;
                    vowel = vowel.next;
                }
            } else {
                if(cH == null) {
                    cH = temp;
                    conso = cH;
                } else {
                    conso.next = temp;
                    conso = conso.next;
                }
            }
            temp.next = null;
        }
        if(vH == null)  return cH;
        vowel.next = cH;
        return vH;
    }
}