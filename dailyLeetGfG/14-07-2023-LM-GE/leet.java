
class twoStacks
{
    int[] arr = new int[101];
    int l = -2;
    int r = -1;
    //Function to push an integer into the stack1.
    void push1(int x)
    {
        l += 2;
        arr[l] = x;
    }
    //Function to push an integer into the stack2.
    void push2(int x)
    {
       r += 2;
       arr[r] = x;
    }
    //Function to remove an element from top of the stack1.
    int pop1()
    {
        if(l < 0)
            return -1;
        int ret = arr[l];
        l -= 2;
        return ret;
    }
    //Function to remove an element from top of the stack2.
    int pop2()
    {
        if(r < 0)
            return -1;
        int ret = arr[r];
        r -= 2;
        return ret;
        
    }
}

