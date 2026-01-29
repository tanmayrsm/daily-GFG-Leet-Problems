class Solution {
    public int[] deckRevealedIncreasing(int[] deck) { // reff
        LinkedList<Integer> list = new LinkedList<>();
        Arrays.sort(deck);
        int n = deck.length;
        int[] res = new int[n];

        for(int i=0; i<n; i++){
            list.offer(i);
        }

        for(int i=0; i<n; i++){
            // System.out.println(list.peek() + "::" + deck[i]);
            res[list.poll()] = deck[i];
            list.offer(list.poll());
        }
        return res;
    }
}
// [2,3,5,7,11,13,17]
// [2, , 3, , 5, , 7]
// 0,1,2,3,4,5,6
// 2,3,4,5,6,1
// 4,5,6,1,3
// 6,1,3,5
// 3,5,1
// 1,5
// 5