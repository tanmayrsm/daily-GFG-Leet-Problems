
//User function Template for Java
class Solution // reff soln - KMP algo
{
    public  void formLpsArray(int[] lps,String pattern){
        int i=1;
        int lMl=0;
        while (i<pattern.length()){
            if(pattern.charAt(i)==pattern.charAt(lMl)){
                lMl++;
                lps[i]=lMl;
                
                i++;
                
            } else {
                if(lMl==0){
                    lps[i]=0;
                    i++;
                } else {
                    lMl=lps[lMl-1];
                }
            }
        }
    }
    
    ArrayList<Integer> search(String pattern, String text)
    {
        int patterLength=pattern.length();
        int textLength=text.length();
        ArrayList<Integer> arrayList=new ArrayList<>();
        int[] lps=new int[patterLength];
        lps[0]=0;
        formLpsArray(lps,pattern);
        int i=0;
        int j=0;

        while(i<textLength){
            
            if(text.charAt(i)==pattern.charAt(j)) {
                i++;
                j++;
                 if (j == patterLength) {
                    arrayList.add((i-j)+1);
                      j=lps[j-1];
                } 
               
            } else  {
                if(j!=0){
                    j=lps[j-1];
                } else {
                    i++;
                }
            }
            
            
        }
        if(arrayList.size()==0){
            arrayList.add(-1);
        }
        return arrayList;
    }
}
