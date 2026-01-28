
class Solution
{
    public String FirstNonRepeating(String A)
    {
        // code here
        int[] charArr = new int[26];
        char[] l = A.toCharArray();
        Queue<Integer> q = new LinkedList<>();
        q.offer(l[0] - 'a');
        charArr[l[0] - 'a']++;
        
        for(int i = 1; i < l.length; i++) {
            int index = l[i] - 'a';
            charArr[index]++;
            if(charArr[index] == 1)
                q.offer(index);
            
            if(q.isEmpty() && charArr[index] > 1) {
                l[i] = '#';
            }
            else if(!q.isEmpty() && index == q.peek()) {    // encountered a duplicate
                while(!q.isEmpty() && charArr[q.peek()] > 1)
                    q.poll();
                if(q.isEmpty())
                    l[i] = '#';
                else l[i] = (char)(q.peek() + 'a');
            } else if(!q.isEmpty() && index != q.peek()) {
                l[i] = (char)(q.peek() + 'a');
            }
        }
        return new String(l);
    }
}