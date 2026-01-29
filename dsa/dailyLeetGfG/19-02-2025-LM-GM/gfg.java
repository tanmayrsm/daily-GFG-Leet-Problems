class Solution {
    // Function to merge K sorted linked lists.
    Node mergeKLists(List<Node> arr) {
        if (arr == null || arr.isEmpty()) return null;

        // Min-heap to store the nodes based on their values
        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.data));

        // Add the head nodes of all lists to the priority queue
        for (Node node : arr) {
            if (node != null) {
                minHeap.add(node);
            }
        }

        // Dummy node to start the merged list
        Node dummy = new Node(0);
        Node tail = dummy;

        // Process the nodes in sorted order
        while (!minHeap.isEmpty()) {
            Node minNode = minHeap.poll(); // Get the smallest node
            tail.next = minNode; // Append it to the result list
            tail = tail.next;

            if (minNode.next != null) {
                minHeap.add(minNode.next); // Push next node from the same list into heap
            }
        }

        return dummy.next; // Return the merged list
    }
}