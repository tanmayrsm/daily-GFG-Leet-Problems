
class Solution {
    int rectanglesInCircle(int r) {
        // code here
        int count=0;

        //apply two nested loops for range of length and breadth with respect two radius
        
        for(int l=1;l<2*r;l++){
            for(int b=1;b<2*r;b++){
                //condition for selecting length and breadth
                if((l*l+b*b)<=4*r*r)
                    count++;
            }
        }
        return count;
    }
};