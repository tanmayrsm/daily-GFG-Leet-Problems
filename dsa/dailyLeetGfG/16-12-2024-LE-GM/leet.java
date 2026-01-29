class Solution {
    public int[] getFinalState(int[] nums, int k, int multiplier) {
        int[]ans = new int[nums.length];

        PriorityQueue<Pair>pq = new PriorityQueue<>((p1, p2) -> {
            if(p1.a != p2.a){
                return Integer.compare(p1.a, p2.a);
            } 
            return Integer.compare(p1.b, p2.b);
        });

        for(int i=0;i<nums.length;i++)pq.add(new Pair(nums[i], i));

        for(int i=0;i<k;i++){
            Pair p = pq.poll();
            p.changeValue(p.a * multiplier);
            pq.add(p);
        }

        for(int i=0;i<nums.length;i++){
            Pair p = pq.poll();
            ans[p.b] = p.a;
        }

        return ans;
    }
}

class Pair{
    int a, b;

    Pair(int c, int d){
        this.a = c;
        this.b = d;
    }

    public void changeValue(int c){
        this.a = c;
    }
}