import java.util.stream.Collectors;

class Solution {
    // Function to sort the array according to frequency of elements.
    public ArrayList<Integer> sortByFreq(int arr[]) {
        // add your code here
        Map<Integer, Integer> freq = new HashMap<>();
        for (int a : arr)
            freq.put(a, freq.getOrDefault(a, 0) + 1);
            
        // Sort the array by frequency and then by value
        List<Integer> ans = Arrays.stream(arr).boxed().sorted((a, b) -> {
            int freqCompare = Integer.compare(freq.get(b), freq.get(a)); // Sort by frequency in descending order
            if (freqCompare == 0) {
                return Integer.compare(a, b); // If frequencies are the same, sort by element in ascending order
            } else {
                return freqCompare;
            }
        }).collect(Collectors.toList());
        
        // Convert the sorted list to an ArrayList
        return new ArrayList<>(ans);
    }
}