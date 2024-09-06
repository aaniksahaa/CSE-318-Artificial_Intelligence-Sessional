import java.util.PriorityQueue;

public class Test {
    public static void main(String[] args) {
        // Create a PriorityQueue (min heap by default)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Insert elements
        minHeap.offer(5);
        minHeap.offer(3);
        minHeap.offer(7);
        minHeap.offer(1);

        // Peek at the minimum element
        System.out.println("Min element: " + minHeap.peek()); // Output: 1

        // Remove and print elements (in ascending order)
        while (!minHeap.isEmpty()) {
            System.out.println("Extracted: " + minHeap.poll());
        }
        // Output:
        // Extracted: 1
        // Extracted: 3
        // Extracted: 5
        // Extracted: 7
    }
}