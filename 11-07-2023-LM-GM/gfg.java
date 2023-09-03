
class Solution
{
    /*You are required to complete this method*/
    int findK(int arr[][], int n, int m, int k)
    {
	    // Your code here	
	    int dir = 0;
	    int x = 0;
	    int y = 0;
	    int currElemCt = 0;
	    boolean br = false;
	    int vis = Integer.MIN_VALUE;
	    while(!br) {
	       // System.out.println("dir :: " + dir);
	        switch (dir) {
	            case 0 : 
	                int j;
	                for(j = y; j < m - y; j++) {    // col iteration - rightwards
	                    currElemCt++;
	                    if(arr[x][j] == vis) {
	                        br = true;
	                        break;
	                    }
	                    if(currElemCt == k)
	                        return arr[x][j];
	                    arr[x][j] = vis;
	                }
	                y = j-1;
	                dir = 1;
	                break;
	            
	            case 1 : 
	                int i;
	                for(i = x + 1; i < n - x; i++) {    // row iteration downwards
	                    currElemCt++;
	                    if(arr[i][y] == vis) {
	                        br = true;
	                        break;
	                    }
	                    if(currElemCt == k)
	                        return arr[i][y];
	                    arr[i][y] = vis;
	                }
	                x = i-1;
	                dir = 2;
	                break;
	            
	            case 2 : 
	                int jj;
	                for(jj = y - 1; jj >= m - y - 1; jj--) {    // col iteration leftwards
	                    currElemCt++;
	                    if(arr[x][jj] == vis) {
	                        br = true;
	                        break;
	                    }
	                    if(currElemCt == k)
	                        return arr[x][jj];
	                    arr[x][jj] = vis;
	                }
	                y = jj+1;
	                dir = 3;
	                break;
	            
	            case 3 : 
	                int ii;
	                for(ii = x - 1; ii > n - x - 1; ii--) { // row iteration upwards
	                    currElemCt++;
	                    if(arr[ii][y] == vis) {
	                        br = true;
	                        break;
	                    }
	                    if(currElemCt == k)
	                        return arr[ii][y];
	                    arr[ii][y] = vis;
	                }
	                x = ii + 1;
	                y = ii + 1;
	                dir = 0;
	                break;
	            
	        }
	    }
	    return 0;
    }
}