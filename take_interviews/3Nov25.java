find first aubarray with strictly increasing numbers of size k
Example 1:
Input: nums = [2,5,7,8,9,2,3,4,3,1], k = 3

Output: 2
[7,8,9] is a subarray with strictly increasing values

nums.length ~ 10^3

// follow up -  could you find two such adjacent subarrays ? with O(n) ?

// next greater element
// https://www.geeksforgeeks.org/dsa/next-greater-element/

Given an array arr[] of integers, determine the Next Greater Element (NGE) for every element in the array, maintaining the order of appearance.

The Next Greater Element for an element x is defined as the first element to the right of x in the array that is strictly greater than x.
If no such element exists for an element, its Next Greater Element is -1.
Examples:

Input: arr[] = [1, 3, 2, 4]
Output: [3, 4, 4, -1]
Explanation: The next larger element to 1 is 3, 3 is 4, 2 is 4 and for 4, since it doesn't exist, it is -1.

Input: arr[] = [6, 8, 0, 1, 3]
Output: [8, -1, 1, 3, -1]
Explanation: The next larger element to 6 is 8, for 8 there is no larger elements hence it is -1, for 0 it is 1 , for 1 it is 3 and then for 3 there is no larger element on right and hence -1.


Constraints : 10^5
//follow up - what if list is circular ?


// house robber

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.



Example 1:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.


18 9 10 1000

18 9 28 1018


Qs

Hi Divya,
I came across recent senior engineer opportunity at Atlassian, lets connect to discuss it further.
PFA - https://drive.google.com/file/d/1p8zd29gwPSOp2S19i7HDLrclG9vE2VSb/view

        9969122803