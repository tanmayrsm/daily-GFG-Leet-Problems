
class Solution{
	static int solve(int i, int m){
	  if(i==0)
		  return 1;
	  if(m==0)
	   return 0;
		  
	  int take=solve(i-1,m/2);
	  int notTake=solve(i,m-1);
	  return take+notTake;   
 }
  static  int numberSequence(int m, int n){
	  return solve(n,m);
  }
}