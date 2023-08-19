class Solution{
	int maxSweetness(int [] sweetness, int N, int K) {
	    // Write your code here.
	    long l = 1;
	    long r = 10000000000000L;
	    long ans = -1;
	    while(l <= r) {
	        long mid = r + (l - r) / 2;
	        if(check(mid, sweetness, N, K)) {
	            ans = mid;
	            l = mid + 1;
	        } else {
	            r = mid - 1;
	        }
	    }
	    return (int) ans;
	}
	
	private static boolean check(long mid, int[] sweetness, int N, int K) {
	    long sum = 0;
	    int piecesMade = 0;
	    for(int i = 0; i < sweetness.length; i++) {
	        sum += sweetness[i];
	        if(sum >= mid) {
	            piecesMade++;
	            sum = 0;
	        }
	    }
	    return piecesMade >= K + 1;
	}
}