class Solution {
  private static List<List<Integer>> ans;
  public List<List<Integer>> combine(int n, int k) {
      ans = new ArrayList<>();
      solve(n, k, new ArrayList<>(), 1);
      return ans;
  }
  private static void solve(int n, int k, List<Integer> l, int currNo) {
      if(l.size() == k) {
          ans.add(new ArrayList<>(l));
          return;
      }
      if(currNo > n)  return;

      l.add(currNo);
      solve(n, k, l, currNo + 1);
      l.remove(l.size() - 1);

      // simple check to know, if its useful to go for computation in case variable is not picked
      // eg - [1,2,3,4,5,6] is input, k = 4, u picked [1], and currNo = 5
      // I know, even if I pick both 5,6 my o/p will be [1,5,6] i.e. always  < k, hence better to not compute it,
      // hence added this check
      if(n - currNo >= k - l.size())  
          solve(n, k, l, currNo + 1);
  }
}