# Bitmasking Formulae & Techniques

## Table of Contents
1. [Check if Power of 2](#check-if-power-of-2)
2. [Get Rightmost Set Bit](#get-rightmost-set-bit)
3. [Get Rightmost Unset Bit](#get-rightmost-unset-bit)
4. [Set a Bit](#set-a-bit)
5. [Clear a Bit](#clear-a-bit)
6. [Toggle a Bit](#toggle-a-bit)
7. [Check if Bit is Set](#check-if-bit-is-set)
8. [Count Set Bits](#count-set-bits)
9. [Other Useful Operations](#other-useful-operations)

---

## Check if Power of 2

### Formula
```java
(n > 0) && ((n & (n - 1)) == 0)
```

### How it Works
Powers of 2 have exactly ONE bit set:
```
1   = 0001
2   = 0010
4   = 0100
8   = 1000
16  = 10000
```

When you subtract 1:
```
8     = 1000
8-1   = 0111

AND:
  1000  (8)
& 0111  (7)
-------
  0000  ✓ Power of 2
```

### Java Examples
```java
// Method 1: Bitwise (fastest)
public boolean isPowerOfTwo(int n) {
    return n > 0 && ((n & (n - 1)) == 0);
}

// Method 2: Using built-in
public boolean isPowerOfTwo(int n) {
    return n > 0 && Integer.bitCount(n) == 1;
}

// Method 3: Using highestOneBit
public boolean isPowerOfTwo(int n) {
    return n > 0 && Integer.highestOneBit(n) == n;
}
```

---

## Get Rightmost Set Bit

### Formula
```java
n & -n
// or
n & (~n + 1)
```

### How it Works
```
n    = 10110000
-n   = 01010000  (two's complement)

n & -n = 00010000  (rightmost set bit isolated)
```

### Java Example
```java
int n = 0b10110000;  // 176
int rightmost = n & -n;  // 16 (0b00010000)

// Get index of rightmost set bit
int index = Integer.numberOfTrailingZeros(n);  // 4
```

### Use Cases
- Finding LSB (Least Significant Bit)
- Fenwick Tree / Binary Indexed Tree operations

---

## Get Rightmost Unset Bit

### Formula
```java
~n & (n + 1)
```

### How it Works
```
n        = 1101
~n       = 0010  (flip all bits)
n + 1    = 1110

~n & (n+1) = 0010  (rightmost unset bit = 2)
```

### Java Example
```java
int n = 0b1101;  // 13
int rightmostUnset = ~n & (n + 1);  // 2 (0b0010)

// Get index
int index = Integer.numberOfTrailingZeros(~n & (n + 1));  // 1
```

---

## Set a Bit

### Formula
```java
n | (1 << i)
```

### How it Works
```
Set bit at index 2 in 1001:
  1001
| 0100  (1 << 2)
-------
  1101  ✓
```

### Java Example
```java
int n = 0b1001;  // 9
int setBit2 = n | (1 << 2);  // 13 (0b1101)
```

---

## Clear a Bit

### Formula
```java
n & ~(1 << i)
```

### How it Works
```
Clear bit at index 2 in 1101:
  1101
& 1011  (~(1 << 2))
-------
  1001  ✓
```

### Java Example
```java
int n = 0b1101;  // 13
int clearBit2 = n & ~(1 << 2);  // 9 (0b1001)
```

---

## Toggle a Bit

### Formula
```java
n ^ (1 << i)
```

### How it Works
```
Toggle bit at index 1 in 1101:
  1101
^ 0010  (1 << 1)
-------
  1111  ✓

Toggle again:
  1111
^ 0010
-------
  1101  ✓
```

### Java Example
```java
int n = 0b1101;  // 13
int toggle = n ^ (1 << 1);  // 15 (0b1111)
int toggleBack = toggle ^ (1 << 1);  // 13 (0b1101)
```

---

## Check if Bit is Set

### Formula
```java
(n & (1 << i)) != 0
// or
((n >> i) & 1) == 1
```

### How it Works
```
Check if bit 2 is set in 1101:
  1101
& 0100  (1 << 2)
-------
  0100  != 0, so YES ✓
```

### Java Example
```java
int n = 0b1101;  // 13

// Method 1
boolean isBit2Set = (n & (1 << 2)) != 0;  // true

// Method 2
boolean isBit1Set = ((n >> 1) & 1) == 1;  // false
```

---

## Count Set Bits

### Formula
```java
// Brian Kernighan's Algorithm
int count = 0;
while (n > 0) {
    n = n & (n - 1);
    count++;
}
```

### How it Works
Each iteration removes the rightmost set bit:
```
n = 1101
1. 1101 & 1100 = 1100  (count=1)
2. 1100 & 1011 = 1000  (count=2)
3. 1000 & 0111 = 0000  (count=3)
Result: 3 set bits
```

### Java Examples
```java
// Method 1: Brian Kernighan's
public int countSetBits(int n) {
    int count = 0;
    while (n > 0) {
        n &= (n - 1);
        count++;
    }
    return count;
}

// Method 2: Built-in (fastest)
public int countSetBits(int n) {
    return Integer.bitCount(n);
}

// Method 3: Lookup table (for small numbers)
int[] lookup = new int[256];
// Pre-populate lookup table
for (int i = 0; i < 256; i++) {
    lookup[i] = (i & 1) + lookup[i / 2];
}
```

---

## Other Useful Operations

### 1. Check if All Bits are Set (in range)
```java
// Check if all bits from 0 to k are set
boolean allSet = (n & ((1 << k) - 1)) == ((1 << k) - 1);
```

### 2. Clear All Bits from MSB to i
```java
int clearMSBtoi = n & ((1 << i) - 1);
```

### 3. Clear All Bits from i to 0
```java
int clearIto0 = n & ~((1 << (i + 1)) - 1);
```

### 4. Get All Set Bits
```java
// Iterate through all set bits
while (n > 0) {
    int rightmost = n & -n;
    int index = Integer.numberOfTrailingZeros(rightmost);
    System.out.println("Bit " + index + " is set");
    n &= (n - 1);  // Remove rightmost set bit
}
```

### 5. Check if Number is Even/Odd
```java
boolean isEven = (n & 1) == 0;
boolean isOdd = (n & 1) == 1;
```

### 6. Multiply/Divide by Power of 2
```java
int multiplyBy4 = n << 2;   // n * 4
int divideBy8 = n >> 3;     // n / 8
```

### 7. Swap Two Numbers
```java
a = a ^ b;
b = a ^ b;  // b = (a ^ b) ^ b = a
a = a ^ b;  // a = (a ^ b) ^ a = b
```

### 8. Find Missing Number in Array [0, n]
```java
public int missingNumber(int[] nums) {
    int xor = nums.length;
    for (int i = 0; i < nums.length; i++) {
        xor ^= i ^ nums[i];
    }
    return xor;
}
```

### 9. Check if Two Numbers Have Opposite Signs
```java
boolean oppositeSigns = (a ^ b) < 0;
```

### 10. Turn Off Rightmost Contiguous 1s
```java
int result = n & (n + 1);
// Example: 10111 & 11000 = 10000
```

---

## Java Built-in Bit Manipulation Methods

```java
// Count set bits
Integer.bitCount(n)

// Number of leading zeros
Integer.numberOfLeadingZeros(n)

// Number of trailing zeros
Integer.numberOfTrailingZeros(n)

// Highest one bit
Integer.highestOneBit(n)  // Returns 1000 for 1101

// Lowest one bit
Integer.lowestOneBit(n)   // Returns 0001 for 1101

// Reverse bits
Integer.reverse(n)

// Rotate left
Integer.rotateLeft(n, distance)

// Rotate right
Integer.rotateRight(n, distance)

// Sign extension
Integer.signum(n)

// Convert to binary string
Integer.toBinaryString(n)
```

---

## Common Patterns & Tricks

### Pattern 1: Isolate Rightmost Different Bit
```java
int diff = a ^ b;
int rightmostDiff = diff & -diff;
```

### Pattern 2: Generate All Subsets
```java
int n = 3;  // Set size
for (int mask = 0; mask < (1 << n); mask++) {
    for (int i = 0; i < n; i++) {
        if ((mask & (1 << i)) != 0) {
            System.out.print(i + " ");
        }
    }
    System.out.println();
}
```

### Pattern 3: Check if Subset
```java
boolean isSubset = (subset & superset) == subset;
```

### Pattern 4: Next Power of 2
```java
public int nextPowerOf2(int n) {
    n--;
    n |= n >> 1;
    n |= n >> 2;
    n |= n >> 4;
    n |= n >> 8;
    n |= n >> 16;
    return n + 1;
}
```

---

## Time Complexities

| Operation | Time Complexity |
|-----------|----------------|
| Set/Clear/Toggle bit | O(1) |
| Check if power of 2 | O(1) |
| Count set bits (Brian Kernighan) | O(number of set bits) |
| Count set bits (Integer.bitCount) | O(1) |
| Get rightmost set/unset bit | O(1) |
| Generate all subsets | O(2^n) |

---

## Important Notes

1. **Operator Precedence**: Always use parentheses with bitwise operators
   ```java
   // Wrong
   if (n & 1 == 0)  // ❌ Evaluates as: n & (1 == 0)

   // Correct
   if ((n & 1) == 0)  // ✓
   ```

2. **Signed vs Unsigned**: Java doesn't have unsigned int, use `>>>` for unsigned right shift
   ```java
   int unsigned = n >>> 1;  // Logical shift (fill with 0)
   int signed = n >> 1;     // Arithmetic shift (preserve sign)
   ```

3. **Overflow**: Be careful with left shift
   ```java
   1 << 31  // OK (still in int range)
   1 << 32  // Same as 1 << 0 (wraps around)
   ```

---

## Practice Problems

- LeetCode 191: Number of 1 Bits
- LeetCode 231: Power of Two
- LeetCode 338: Counting Bits
- LeetCode 268: Missing Number
- LeetCode 136: Single Number
- LeetCode 137: Single Number II
- LeetCode 260: Single Number III
- LeetCode 78: Subsets
- LeetCode 698: Partition to K Equal Sum Subsets (bitmask DP)

---

**Last Updated**: 2025-11-19
