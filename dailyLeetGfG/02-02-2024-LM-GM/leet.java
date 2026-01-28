class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        int n1 = String.valueOf(low).length(), n2 = String.valueOf(high).length();
        return getIncrementalNosBetweenDigits(n1, n2, low, high);
    }

    private List<Integer> getIncrementalNosBetweenDigits(int digitsInX, int digitsInY, int low, int high) {
        List<Integer> output = new ArrayList<>();
        for(int i = digitsInX; i <= digitsInY; i++) {
            // if no of digits == 10 i.e 10^9, skip the loop, 
            // as initial would be => 1234567890, from 10^9 onwards,
            // incremental nos would be 0
            if(i >= 10) break;
            
            int start = 0;
            for(int j = 1; j <= i; j++)
                start = start * 10 + j; // geenrates 1234... upto i digits
            char[] val = String.valueOf(start).toCharArray();
            int valLen = val.length;
            while(true) {
                int lastNo = Integer.parseInt(String.valueOf(val));
                if(lastNo >= low && lastNo <= high)
                    output.add(lastNo);

                // 1234 => 2345
                if(val[valLen - 1] == '9')   break;
                for(int k = 0; k < valLen; k++) {
                    // main part to increment digit and convert char -> int ( then increment) -> char
                    int res = (val[k] - '0') + 1;
                    val[k] = (char)(res + '0');
                }
            }
        }
        
        return output;
    }
}

// Input: low = 1000, high = 13000
// Output: [1234,2345,3456,4567,5678,6789,12345]

// n1 = 4, n2 = 5

// i = 4 to 5

// i = 4 ->
//     start = 1234
//     val = [1,2,3,4]

//     // [1234]
//     val = 2345


// 1- how to get first no
// 2- once u get first no, write an algo, which increments each digit, and checks in range
//     - okay, if last digit 9, then consider next incremental no in 'n + 1' range, where 'n' is curren no of digit


// [12,23,34,45,56,67,78,89,123,234,345,456,567,678,789,1234]
// 1- no of dig in low, no of dig in high, add all elems in this digit range in List
// 2- do a normal low to high range checker and add in result list