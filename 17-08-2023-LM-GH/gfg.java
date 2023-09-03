
class Solution {

    Vector<Integer> generateNextPalindrome(int num[], int n) {
        // code here
        int i=(n-1)/2;
        int j=n/2;
        boolean in=isnine(num);
        if(in){
              Vector<Integer> v = new Vector<Integer>(n+1);
        for(int k=0;k<n+1;k++){
            if(k==0 ||k==n){
                v.add(1);
            }else{
            v.add(0);
        }}
        return v;
        }
        boolean flag=is(num);

        while(i>=0){
            if(flag){
                 if(num[i]==9){
                     num[i]=0;
                     flag=true;
                 }
                 else{
                     num[i]++;
                     flag=false;
                 }
            }
            num[j++]=num[i--];
        }
        Vector<Integer> v = new Vector<Integer>(n);
        for(int k=0;k<n;k++){
            v.add(num[k]);
        }
        return v;
    }
  public boolean is(int []num){
      int n=num.length;
      int i=(n-1)/2;
      int j=(n)/2;
      while(i>=0){
           if(num[i]<num[j]){
               return true;
           }
        else if(num[i] > num[j]) {return false;}
           i--;
           j++;
      }
      return true;
  }
  public boolean isnine(int []num){
        for(int i=0;i<num.length;i++){
            if(num[i]!=9){
                return false;
            }
        }
        return true;
  }
}