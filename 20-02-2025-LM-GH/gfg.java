class Solution {
    public void addNum(int num,PriorityQueue<Integer> leftPq,PriorityQueue<Integer> rightPq) {
        if(leftPq.size() == rightPq.size()){
            rightPq.add(num);
            leftPq.add(rightPq.remove());
        } else {
            leftPq.add(num);
            rightPq.add(leftPq.remove());
        }
    }
    
    public void findMedian(ArrayList<Double> ans,PriorityQueue<Integer> leftPq,PriorityQueue<Integer> rightPq){
        if(leftPq.size() == rightPq.size()){
            ans.add((leftPq.peek() + rightPq.peek())/2.0);
        } else {
            ans.add(leftPq.peek() * 1.0);
        }
    }
    public ArrayList<Double> getMedian(int[] arr) {
        // code here
        PriorityQueue<Integer> leftPq = new PriorityQueue<>((a,b) -> Integer.compare(b,a)); 
        PriorityQueue<Integer> rightPq = new PriorityQueue<>(); 
        ArrayList<Double> ans = new ArrayList<>();
        
        for(int i=0;i<arr.length;i++){
            addNum(arr[i],leftPq,rightPq);
            findMedian(ans,leftPq,rightPq);
        }
        return ans;
    }
}