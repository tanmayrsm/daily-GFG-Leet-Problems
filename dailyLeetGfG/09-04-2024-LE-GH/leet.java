class Solution {    // reff
    public int timeRequiredToBuy(int[] tickets, int k) {
        int val = tickets[k], res=0;
        for(int i=0; i<tickets.length; i++){
            if(i<=k){
                res = res+Math.min(tickets[i], val);
            } else{
                res = res+Math.min(tickets[i], val-1);
            }
        }
        return res;
    }
}

// 20 30 20
// 20*3 = 60

// 10 20 30 40
// k = 3
// 10*4 + (20-10)*3 + ()

// 10 22 34 45
// 0 12 24 35
// 0 0 12 23
// 0 0 0 11

// 10*4 + 12*3 + 12*2 + 11*1
// 40 + 36 + 24 + 11 = 111

// // problem with duplicates
// 3 10 10 10 34...k = 2
// 0 7 7 7 31
// 0 0 0 0 24

// 3*5 + 7*4 = 43

// 34 10 3 10 10
// 31 7 0 7 7 => 3 turns
// 24 0 0 0 0

// 10 3 10 10 33
// 3 10 10 33 9
// 10 10 33 9 2

// 13th turn 3 => 0
// 15th second => 31 7 0 7 7

// 31 7 7 7 => 15
// 25 1 1 1 => 24 + 15 => 39
// 24 1 1 1 => 40
// 24 0 1 1 => 41


// IN SHORT =>
// 34 10 3 10 10, for k = 1
// 10 + 10 + 3 + 9 + 9