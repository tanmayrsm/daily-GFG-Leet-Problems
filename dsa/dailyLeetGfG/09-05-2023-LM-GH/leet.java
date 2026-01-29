class Solution {
    public List<Integer> getOrderN(int[][] matrix, int[] upperLimit, int[] lowerLimit){
        // take 4 turns, or just iterate one row, if upperLimit === lowerLimit
        // upperlimit[0] -> top row no
        // upperlimit[1] -> top col no
        
        List<Integer> res = new ArrayList<Integer>();
        if(upperLimit[0] == lowerLimit[0]) {
            // System.out.println("ok");
            for(int i = upperLimit[1]; i <= lowerLimit[1]; i++){
                res.add(matrix[upperLimit[0]][i]);
            }
        } else {
            // trace left to right
            for(int i = upperLimit[1]; i <= lowerLimit[1]; i++) 
                res.add(matrix[upperLimit[1]][i]);
            
            // trace top to bottom
            for(int i = upperLimit[0] + 1; i <= lowerLimit[0]; i++){
                // System.out.println("out of bound ::" + i + " :: " +lowerLimit[1]);
                res.add(matrix[i][lowerLimit[1]]);
            }
                
            // trace right to left
            for(int i = lowerLimit[1] - 1; i >= upperLimit[0]; i--) 
                res.add(matrix[lowerLimit[0]][i]);
            
            // trace bottom to top - 1
            for(int i = lowerLimit[0] - 1; i > upperLimit[1] && upperLimit[1] != lowerLimit[1] ; i--){ // also added edge case in condition, of array with only one column,
                res.add(matrix[i][upperLimit[1]]);
            }
        }
        // System.out.println("");
        // for(int i = 0; i < res.size(); i++) {
        //     System.out.print(res.get(i) + " ");
        // }
        return res;
    }
    public List<Integer> spiralOrder(int[][] matrix) {
        int i = 0;
        int j = matrix.length - 1;
        int k = matrix[0].length - 1;
        
        List<Integer> res = new ArrayList<Integer>();
        if(j == 0 && k == 0) {
            res.add(matrix[0][0]);
            return res;
        }
        
        if(k == 0){
            for(int h = 0; h < matrix.length; h++)
                res.add(matrix[h][0]);
            return res;
        }
        while(i <= j && k > 0) {
            // System.oumt.println("i ::" + i + " :: j ::" + j +"::k::" + k);
            res.addAll(getOrderN(matrix, new int[]{i,i}, new int[]{j,k}));
            i++;
            j--;
            k--;
            if(matrix[0].length == 1) {
                break;
            }
        }
        return res;
        
    }
}