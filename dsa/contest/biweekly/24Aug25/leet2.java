class Solution {
    public int score(String[] cards, char x) {
        int n = cards.length, ans = 0, xAtBoth = 0;
        int[] xAt0 = new int[10], xAt1 = new int[10];
        for (String card : cards) {
            char c1 = card.charAt(0), c2 = card.charAt(1);
            if (c1 == x && c2 == x) {
                xAtBoth++;
            }
            else if (c1 == x) xAt0[c2 - 'a']++;
            else if (c2 == x) xAt1[c1 - 'a']++;
        }
        Arrays.sort(xAt0);
        Arrays.sort(xAt1);
        for (int i = 9; i >= 0; i--)
            System.out.println(xAt0[i] + "::" + xAt1[i]);
        int X = deduct(xAt0), Y = deduct(xAt1);
        System.out.println(X + "<>>" + Y + "::" + xAtBoth);
        for (int i = 9; i >= 0; i--)
            System.out.println(xAt0[i] + "::" + xAt1[i]);

        ans += X + Y;
        for (int i = 0; i < xAt0.length; i++) {
            if (xAt0[i] > 0 && xAtBoth > 0) {
                if (xAt0[i] == xAtBoth) {
                    ans += xAt0[i];
                    xAt0[i] = 0;
                    xAtBoth = 0;
                } else if (xAt0[i] > xAtBoth) {
                    ans += xAtBoth;
                    xAt0[i] -= xAtBoth;
                    xAtBoth = 0;
                } else {
                    ans += xAt0[i];
                    xAtBoth -= xAt0[i];
                    xAt0[i] = 0;
                }
            }
        }
        System.out.println(xAtBoth + "::::::" + ans);

        for (int i = 0; i < xAt1.length; i++) {
            if (xAt1[i] > 0 && xAtBoth > 0) {
                if (xAt1[i] == xAtBoth) {
                    ans += xAt1[i];
                    xAt1[i] = 0;
                    xAtBoth = 0;
                } else if (xAt1[i] > xAtBoth) {
                    ans += xAtBoth;
                    xAt1[i] -= xAtBoth;
                    xAtBoth = 0;
                } else {
                    ans += xAt1[i];
                    xAtBoth -= xAt1[i];
                    xAt1[i] = 0;
                }
            }
        }

        System.out.println(xAtBoth + "::::::" + ans);
        return ans;
    }
    private int deduct(int[] num) {
        int i = num.length - 1, ans = 0;
        while (i > 0) {
            if (num[i] > 0) {
                int j = i - 1;
                while (num[i] > 0 && j >= 0) {
                    if (num[j] > 0) {
                        if (num[i] == num[j]) {
                            ans += num[i];
                            num[i] = 0;
                            num[j] = 0;
                        } else if (num[i] > num[j]) {
                            ans += num[j];
                            num[i] -= num[j];
                            num[j] = 0;
                        } else {
                            ans += num[i];
                            num[j] -= num[i];
                            num[i] = 0;
                        }
                    }
                    j--;
                }
            }
            i--;
        }
        return ans;
    }
}
x = b
ba bb ab



You are given a deck of cards represented by a string array cards, and each card displays two lowercase letters.

You are also given a letter x. You play a game with the following rules:

Start with 0 points.
On each turn, you must find two compatible cards from the deck that both contain the letter x in any position.
Remove the pair of cards and earn 1 point.
The game ends when you can no longer find a pair of compatible cards.
Return the maximum number of points you can gain with optimal play.

Two cards are compatible if the strings differ in exactly 1 position.



        Example 1:

Input: cards = ["aa","ab","ba","ac"], x = "a"

Output: 2

Explanation:

On the first turn, select and remove cards "ab" and "ac", which are compatible because they differ at only index 1.
On the second turn, select and remove cards "aa" and "ba", which are compatible because they differ at only index 0.
Because there are no more compatible pairs, the total score is 2.

Example 2:

Input: cards = ["aa","ab","ba"], x = "a"

Output: 1

Explanation:

On the first turn, select and remove cards "aa" and "ba".
Because there are no more compatible pairs, the total score is 1.

Example 3:

Input: cards = ["aa","ab","ba","ac"], x = "b"

Output: 0

Explanation:

The only cards that contain the character 'b' are "ab" and "ba". However, they differ in both indices, so they are not compatible. Thus, the output is 0.



Constraints:

        2 <= cards.length <= 105
cards[i].length == 2
Each cards[i] is composed of only lowercase English letters between 'a' and 'j'.
x is a lowercase English letter between 'a' and 'j'.