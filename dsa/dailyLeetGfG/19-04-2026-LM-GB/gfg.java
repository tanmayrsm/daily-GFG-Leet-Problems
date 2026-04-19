class Solution {
    public boolean isPower(int x, int y) {
        // code here
        // code here
        if(y==1)return true;
        if(x==1)return false;
        if(x==0)return false;
        while(y>0){
            if(x==y)return true;
            if(y%x==0){
                y=y/x;
            }else{
                return false;
            }
        }
        return false;
    }
}