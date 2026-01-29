ArrayList<Integer> ansList= new ArrayList<>();
        
        Arrays.sort(prices);
        int left=0,right=prices.length-1;
        int miniAmt=0,maxAmt=0;
        
        while(left<=right){
miniAmt=miniAmt+prices[left];
left++;
right=right-k;
        }
                ansList.add(miniAmt);

left=0;
right=prices.length-1;
        while(left<=right){
maxAmt=maxAmt+prices[right];
right--;
left=left+k;
        }
                ansList.add(maxAmt);
        
        return ansList;