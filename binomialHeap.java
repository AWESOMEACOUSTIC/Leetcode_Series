import java.util.*; // Import utility classes for input and data structures

public class binomialHeap { // Main class
    // Node class for binomial heap
    static class BinomialNode {
        int key;               // The key value of this node
        int degree;            // Number of children (degree of the node)
        BinomialNode parent;   // Pointer to the parent node
        BinomialNode child;    // Pointer to the leftmost child node
        BinomialNode sibling;  // Pointer to the next sibling node

        // Constructor to create a new node with given key
        BinomialNode(int key) {
            this.key = key;    // Set key value
            degree = 0;        // Initially, no children so degree is 0
            parent = null;     // No parent initially
            child = null;      // No child initially
            sibling = null;    // No sibling initially
        }
    }

    // Binomial Heap class
    static class BinomialHeap {
        BinomialNode head;  // Head of the root list of binomial trees

        // Constructor: creates an empty binomial heap
        BinomialHeap() {
            head = null; // Initially, heap is empty
        }

        // Merge two root lists sorted by degree and return the merged list
        private BinomialNode merge(BinomialNode h1, BinomialNode h2) {
            if (h1 == null) return h2;         // If first list is empty, return second
            if (h2 == null) return h1;         // If second list is empty, return first

            BinomialNode headMerged;           // Head pointer for merged list
            BinomialNode tailMerged;           // Tail pointer for merged list

            // Choose the head based on the smaller degree
            if (h1.degree <= h2.degree) {
                headMerged = h1;
                h1 = h1.sibling;               // Advance h1
            } else {
                headMerged = h2;
                h2 = h2.sibling;               // Advance h2
            }
            tailMerged = headMerged;           // Initialize tail

            // Merge the two lists
            while (h1 != null && h2 != null) {
                if (h1.degree <= h2.degree) {
                    tailMerged.sibling = h1;
                    h1 = h1.sibling;
                } else {
                    tailMerged.sibling = h2;
                    h2 = h2.sibling;
                }
                tailMerged = tailMerged.sibling; // Move tail pointer
            }
            tailMerged.sibling = (h1 != null) ? h1 : h2; // Attach remaining nodes
            return headMerged;               // Return merged root list
        }

        // Link two binomial trees of the same degree; make y a child of z
        private void binomialLink(BinomialNode y, BinomialNode z) {
            y.parent = z;             // Set z as parent of y
            y.sibling = z.child;      // Insert y as the leftmost child of z
            z.child = y;              // Update child pointer of z
            z.degree++;               // Increase degree of z by 1
        }

        // Union this heap with another heap
        public void union(BinomialHeap other) {
            head = merge(this.head, other.head); // Merge the two root lists
            other.head = null;                     // Clear the other heap

            if (head == null) return;              // If merged heap is empty, return

            BinomialNode prev = null;              // Previous pointer in root list
            BinomialNode curr = head;              // Current pointer in root list
            BinomialNode next = curr.sibling;      // Next pointer in root list

            // Traverse the merged root list to fix trees of same degree
            while (next != null) {
                if (curr.degree != next.degree || (next.sibling != null && next.sibling.degree == curr.degree)) {
                    prev = curr;               // Move prev to current if degrees differ or triple conflict
                    curr = next;               // Advance current pointer
                } else {
                    if (curr.key <= next.key) { // If current's key is smaller, link next under current
                        curr.sibling = next.sibling;
                        binomialLink(next, curr);
                    } else {                    // Otherwise, link current under next
                        if (prev == null)
                            head = next;
                        else
                            prev.sibling = next;
                        binomialLink(curr, next);
                        curr = next;
                    }
                }
                next = curr.sibling;           // Advance next pointer
            }
        }

        // Insert a new key into the binomial heap
        public void insert(int key) {
            BinomialHeap tempHeap = new BinomialHeap(); // Create a temporary heap
            tempHeap.head = new BinomialNode(key);        // New heap with single node
            this.union(tempHeap);                         // Union the temporary heap with current heap
        }

        // Find and return the minimum key in the heap
        public int findMin() {
            if (head == null) return Integer.MAX_VALUE; // Return infinity if heap is empty
            int min = head.key;                           // Initialize min as key of head node
            BinomialNode curr = head;                     // Pointer to traverse root list
            while (curr != null) {
                if (curr.key < min) {                     // Update min if smaller key is found
                    min = curr.key;
                }
                curr = curr.sibling;                      // Move to next root
            }
            return min;                                   // Return minimum key
        }

        // Extract and return the minimum key from the heap
        public int extractMin() {
            if (head == null) return Integer.MAX_VALUE; // Return infinity if heap is empty

            // Find the root with the minimum key
            BinomialNode prevMin = null, minNode = head;
            BinomialNode prev = null, curr = head;
            while (curr != null) {
                if (curr.key < minNode.key) { // Update minNode if a smaller key is found
                    minNode = curr;
                    prevMin = prev;
                }
                prev = curr;
                curr = curr.sibling;
            }

            // Remove minNode from the root list
            if (prevMin == null)
                head = minNode.sibling;
            else
                prevMin.sibling = minNode.sibling;

            // Reverse minNode's children to form a new heap
            BinomialNode child = minNode.child;
            BinomialNode newHead = null;
            while (child != null) {
                BinomialNode nextChild = child.sibling;
                child.sibling = newHead;
                child.parent = null;
                newHead = child;
                child = nextChild;
            }

            // Create a new heap from the reversed children
            BinomialHeap newHeap = new BinomialHeap();
            newHeap.head = newHead;
            // Union the new heap with the current heap
            this.union(newHeap);

            return minNode.key; // Return the key of the extracted node
        }

        // Print the root list of the binomial heap (for demonstration)
        public void printHeap() {
            System.out.print("Binomial Heap: ");
            BinomialNode curr = head;
            while (curr != null) {
                System.out.print("B" + curr.degree + "(" + curr.key + ") "); // Print degree and key
                curr = curr.sibling;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Create a Scanner object for user input
        BinomialHeap heap = new BinomialHeap(); // Initialize an empty binomial heap

        // Read the number of elements to insert
        System.out.println("Enter number of elements to insert:");
        int n = sc.nextInt();

        // Insert each element into the binomial heap
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            int key = sc.nextInt(); // Read a key
            heap.insert(key);       // Insert the key into the heap
        }

        // Display the heap's root list
        heap.printHeap();

        // Show the minimum key in the heap
        System.out.println("Minimum key in the heap: " + heap.findMin());

        // Extract the minimum key and display it
        System.out.println("Extracting minimum key...");
        int minKey = heap.extractMin();
        System.out.println("Extracted minimum key: " + minKey);

        // Print the heap's root list after extraction
        heap.printHeap();
        sc.close(); // Close the scanner to free resources
    }
}
