
class Solution{
	int [] nearestSmallestTower(int [] arr){
		//Write your code here
		int n = arr.length;
		int[] ans = new int[n];
		Stack<Integer> st = new Stack<>();
		st.push(n-1);
		ans[n-1] = -1;
		
		// track all smallest from right to left
		for(int i = n - 2; i >= 0; i--) {
		    if(arr[st.peek()] < arr[i]) {
		        ans[i] = st.peek();
		        st.push(i);
		    } else {
		        while(!st.isEmpty() && arr[st.peek()] >= arr[i])
		            st.pop();
	            if(st.isEmpty())
	                ans[i] = -1;
                else ans[i] = st.peek();
                st.push(i);
		    }
		}
		
		while(!st.isEmpty())
		    st.pop();
	    st.push(0);
	    int curr = -1;
	    
	    // track all smallest from left to right
	    // and compute final ans array
		for(int i = 0; i < n; i++) {
		    // same logic as above
		    if(arr[st.peek()] < arr[i]) {
		        curr = st.peek();
		        st.push(i);
		    } else {
		        while(!st.isEmpty() && arr[st.peek()] >= arr[i])
		            st.pop();
	            if(st.isEmpty())
	                curr = -1;
                else curr = st.peek();
                st.push(i);
		    }
		    
		    // compute final ans[i]
		    if(ans[i] == -1)
	            ans[i] = curr;
            else if(curr == -1)
                ans[i] = ans[i];
            else if(ans[i] == -1 && curr == -1)
                ans[i] = -1;
            else {
                int lem1 = Math.abs(i - ans[i]);
                int lem2 = Math.abs(i - curr);
                if(lem1 == lem2) {  // both minimum are at equal distance from 'i'
                    if(arr[ans[i]] == arr[curr])    // both minimum indices are same nos, go with smaller index
                        ans[i] = Math.min(ans[i], curr);
                    else {
                        ans[i] = arr[ans[i]] < arr[curr] ? ans[i] : curr;   // both minimum indices arent same, go with smaller no
                    }
                } else if(lem1 > lem2) {  // lem1, index of first elem is > index of second, go with lem2 aka curr  
                    ans[i] = curr;
                }   // else let ans[i] stay as it is, as lem1 is closer, which was already computed
            }
		}
		return ans;
		
	}
}