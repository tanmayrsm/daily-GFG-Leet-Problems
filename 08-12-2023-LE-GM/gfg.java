
class Solution
{
    // assumption based solution
    public int minNumber(int arr[], int N) {
        int sum = Arrays.stream(arr).sum();
        return findNextPrime(sum) - sum;
    }
    private static int findNextPrime(int sum) {
        for(int i = sum; i < sum + 100; i++) {  // assuming the next prime no lies within range of 100 for this number, though it can be true always
            if(isPrime(i))  return i;
        }
        return -1;
    }
    private static boolean isPrime(int no) {
        for(int i = 2; i * i <= no; i++) {
            if(no % i == 0)  return false;
        }
        return true;
    }
}


// 18 + x = prime no after 18
// 1 -> p = find prime no after n
// return p - n;

// 20 - 21 - 22 - 23