class GfG{
    //Function to reverse the queue.
    public Queue<Integer> rev(Queue<Integer> q){
        //add code here.
        Deque<Integer> rev =new ArrayDeque<Integer>();
        
        while(!q.isEmpty())
            rev.offerFirst(q.poll());

        return Collections.asLifoQueue(rev);
    }
}