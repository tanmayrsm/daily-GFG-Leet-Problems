class Bank {
    int n;
    long[] balance;
    public Bank(long[] balance) {
        this.balance = balance;
        n = balance.length;
    }

    public boolean transfer(int account1, int account2, long money) {
        if ((account1 >= 1 && account2 >= 1 && account1 <= n && account2 <= n) && withdraw(account1, money)) {  // yes, need this extra if check for bounds, to ensure withdraw and deposit both are in sync, incase withdraw happends, deposit must surely happen
            return deposit(account2, money);
        }
        return false;
    }

    public boolean deposit(int account, long money) {
        if (account >= 1 && account <= n)   {
            balance[account - 1] += money;
            return true;
        }
        return false;
    }

    public boolean withdraw(int account, long money) {
        if (account >= 1 && account <= n && balance[account - 1] - money >= 0)   {
            balance[account - 1] -= money;
            return true;
        }
        return false;
    }
}

/**
 * Your Bank object will be instantiated and called as such:
 * Bank obj = new Bank(balance);
 * boolean param_1 = obj.transfer(account1,account2,money);
 * boolean param_2 = obj.deposit(account,money);
 * boolean param_3 = obj.withdraw(account,money);
 */