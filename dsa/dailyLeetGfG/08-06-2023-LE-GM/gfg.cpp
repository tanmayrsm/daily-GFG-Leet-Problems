
class Solution
{
public:
    int fact[10] = {1,1,2,6,24,120,720,5040,40320,362880};
    string kthPermutation(int n, int k) // referred soln
    {
        // code here
        // insert numbers 1 to N in to set
        set<int>nums;
        for(int i=1;i<=n;i++)nums.insert(i);
         
        // resulting string
        string str = "";
         
        permutation(n,k,nums,str);
         
        return str;
    }
    
     
    // recursively insert numbers in to string
    void permutation(int n, int k, set<int>&nums, string &str)
    {
        // base case n==0 then no numbers to process
        if(n==0) return;
         
        int val;
         
        // base case k=1 then add numbers from begin
        // base case k=0 then next numbers to be added will be in reverse from rbegin
        // k<=fact[n-1] then add the begin number
        if(k<=1 || k<=fact[n-1])
        {
            val = k==0 ? *nums.rbegin() : *nums.begin(); 
        }
        else
        {
            // calculate number of values cover k => k/fact[n-1]
            // so next value index => k/fact[n-1]
            int index = k/fact[n-1];               
            k = k %fact[n-1];   // remaining permutations
 
            // also if k%fact[n-1] == 0 then kth permutation covered by value is in index-1
            // EX: [2,3] n=2, k=2 => index  = k/fact[n-1] = 2/1 = 2
            // as k%fact[n-1] => 2%1 = 0, so decrease index to 1
            // so we take the value 3 as next value
 
            if(k==0)index--;
         
            // value taken
            val = *next(nums.begin(),index);
        }
         
        // add value to the string and remove from set
        str+= to_string(val); 
        nums.erase(val);
                 
        // decrement n in each step
        return permutation(n-1,k,nums,str);
    }
     

};