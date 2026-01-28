/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
    int curr, n;
    List<Integer> l;
    public NestedIterator(List<NestedInteger> nestedList) {
        l = new ArrayList<>();
        fill(nestedList);
        curr = 0;
        n = l.size();
    }

    @Override
    public Integer next() {
        curr++;
        return l.get(curr - 1);
    }

    @Override
    public boolean hasNext() {
        return curr < n;
    }

    private void fill(List<NestedInteger> g) {
        for(NestedInteger x :  g) {
            if(x.isInteger())
                l.add(x.getInteger());
            else fill(x.getList());
        }
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */