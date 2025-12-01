class Solution {

    class TrieNode {
        TrieNode[] child = new TrieNode[2];
        int count = 0;
    }

    class Trie {
        TrieNode root = new TrieNode();

        void insert(int num) {
            TrieNode node = root;
            for (int i = 16; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (node.child[bit] == null)
                    node.child[bit] = new TrieNode();
                node = node.child[bit];
                node.count++;
            }
        }

        int countLess(int num, int k) {
            TrieNode node = root;
            int cnt = 0;
            for (int i = 16; i >= 0; i--) {
                if (node == null) break;
                int numBit = (num >> i) & 1;
                int kBit = (k >> i) & 1;

                if (kBit == 1) {
                    if (node.child[numBit] != null)
                        cnt += node.child[numBit].count;
                    node = node.child[1 - numBit];
                } else {
                    node = node.child[numBit];
                }
            }
            return cnt;
        }
    }

    public int cntPairs(int[] arr, int k) {
        Trie trie = new Trie();
        int result = 0;
        for (int num : arr) {
            result += trie.countLess(num, k);
            trie.insert(num);
        }
        return result;
    }
}
