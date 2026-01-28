//  price = [7,10,4]
// k = 10
// greedy - take only least priced
// [7/1, 10/2, 4/3]

      
        
// k, cost,qty
// cost*qty <= k => k - cost*qty
// cost*qty > k
    // cost = 8, qty = 10, k = 60
    // k/cost = 7, total qty, I can accomodate
    // k -= (k/cost)*cost
    // k = 60 - 7*8
    // k = 4



class Solution {
    public static int buyMaximumProducts(int n, int k, int[] price) {
        // code here
        int maxProductBought = 0;
        PriorityQueue<Product> products = new PriorityQueue<>((prod1, prod2) -> 
                        Integer.compare(prod1.cost, prod2.cost));
        for(int i = 0; i < n; i++)
            products.offer(new Product(price[i], i + 1));
        
        while(!products.isEmpty() && k > 0) {
            Product product = products.poll();
            // System.out.println(product.cost + "::" +product.qty + "::" + k);
            if(product.cost * product.qty <= k) {
                maxProductBought += product.qty;
                k -= product.cost * product.qty;
            }
            else if (product.cost != 0) {  // INFINITE error prevention
                maxProductBought += k / product.cost;
                k = 0;  // no need to check further, as next element has more cost than current
            }
        }
        return maxProductBought;
    }
    static class Product {
        int cost;
        int qty;
        Product(int c, int q) {
            this.cost = c;
            this.qty = q;
        }
    }
}