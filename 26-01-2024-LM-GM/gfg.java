
/*
class Item {
    int value, weight;
    Item(int x, int y){
        this.value = x;
        this.weight = y;
    }
}
*/

class Solution
{
    //Function to get the maximum total value in the knapsack.
    double fractionalKnapsack(int W, Item arr[], int n) 
    {
        // Your code here
        return getMaxValue(arr, W);
    }
    
    private static double getMaxValue(Item[] arr,
                                      int capacity) {
        // Sorting items by profit/weight ratio;
        Arrays.sort(arr, new Comparator<Item>() {
            @Override
            public int compare(Item item1,
                               Item item2)
            {
                double cpr1
                    = new Double((double)item1.value
                                 / (double)item1.weight);
                double cpr2
                    = new Double((double)item2.value
                                 / (double)item2.weight);
 
                if (cpr1 < cpr2)
                    return 1;
                else
                    return -1;
            }
        });
 
        double totalValue = 0d;
 
        for (Item i : arr) {
 
            int curWt = (int)i.weight;
            int curVal = (int)i.value;
 
            if (capacity - curWt >= 0) {
 
                // This weight can be picked whole
                capacity = capacity - curWt;
                totalValue += curVal;
            }
            else {
 
                // Item cant be picked whole
                double fraction
                    = ((double)capacity / (double)curWt);
                totalValue += (curVal * fraction);
                capacity
                    = (int)(capacity - (curWt * fraction));
                break;
            }
        }
 
        return totalValue;
    }
}