class Solution {
    boolean sameFreq(String s) {
        // code here
        int n=s.length();
        if(n==0)return true;
        int freq[]=new int[26];
        for(int i=0;i<n;i++)
        {
            char ch=s.charAt(i);
            freq[ch-'a']++;
        }
        int freq1=-1;
        int freq2=-1;
        int c1=0;
        int c2=0;
        for(int i=0;i<26;i++)
        {
            if(freq[i]==0)continue;
            if(freq1==-1)
            {
                freq1=freq[i];
                c1++;
            }
            else if(freq[i]==freq1)c1++;
            else if(freq2==-1)
            {
                freq2=freq[i];
                c2++;
            }
            else if(freq[i]==freq2)c2++;
            else return false;
        }
        if(c2==0)return true;
        if((c1==1 && freq1==1) || (c2==1 && freq2==1))return true;
        if(c1==1 && freq1-freq2==1)return true;
        if(c2==1 && freq2-freq1==1)return true;
        return false;

    }
}

xyyz -> 1:2, 2:1



