import java.util.ArrayList;
import java.util.Scanner;

public class kary_heap {
    private static int n;
    private static ArrayList<Integer> arl = new ArrayList<>();

    public static int getMax() {
        if (arl.isEmpty()) { // Check if the heap is empty
            return -1; // Return the smallest integer if empty (indicating error)
        }
        return arl.get(0); // Otherwise, return the first element which is the maximum
    }

    // Function to insert a new value into the k-ary heap
    public static void insert(int value) {
        arl.add(value); // Add the new value at the end of the ArrayList
        int childrenIndex = arl.size() - 1; // Get the index of the newly added element
        int parentIndex = (childrenIndex - 1) / n; // Calculate the parent's index based on k-ary heap formula

        while (parentIndex >= 0 && arl.get(childrenIndex) > arl.get(parentIndex)) {
            int temp = arl.get(childrenIndex); // Temporarily store the child's value
            arl.set(childrenIndex, arl.get(parentIndex)); // Replace child's value with parent's value
            arl.set(parentIndex, temp); // Set parent's value to the stored child value
            childrenIndex = parentIndex; // Update child index to parent's index after swapping
            parentIndex = (childrenIndex - 1) / n; // Recalculate the parent's index for the new child index
        }
    }

    // Function to remove the maximum element (root) from the heap
    public static void removeMax() {
        if (arl.isEmpty()) { // Check if the heap is empty
            System.out.println("Heap is empty. Cannot remove the maximum element."); // Print an error message
            return; // Exit the function as there's nothing to remove
        }
        arl.set(0, arl.get(arl.size() - 1)); // Replace the root element with the last element in the heap
        arl.remove(arl.size() - 1); // Remove the last element from the ArrayList
        int parentIndex = 0; // Start at the root index for re-heapifying

        // Bubble down the element at the root to restore the max-heap property
        while (true) {
            int largestValueIndex = parentIndex; // Assume the current node is the largest
            // Iterate through all k children of the current node
            for (int i = n * parentIndex + 1; i <= (n * parentIndex + n) && i < arl.size(); i++) {
                if (arl.get(largestValueIndex) < arl.get(i)) { // If a child is larger than the current largest
                    largestValueIndex = i; // Update the index of the largest value
                }
            }
            // If no child is larger than the current node, then the heap property is satisfied
            if (largestValueIndex == parentIndex) {
                break; // Exit the loop since the correct position is found
            } else {
                int temp = arl.get(parentIndex); // Temporarily store the parent's value
                arl.set(parentIndex, arl.get(largestValueIndex)); // Swap parent's value with the larger child's value
                arl.set(largestValueIndex, temp); // Set the child's value to the stored parent's value
                parentIndex = largestValueIndex; // Update the parent's index to continue bubbling down
            }
        }
    }

    // Main method: entry point of the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object for user input

        // Prompt the user to input the value of k (number of children per node)
        System.out.println("Enter the value of k (number of children per node):");
        n = scanner.nextInt(); // Read the integer value for k

        // Prompt the user to input the number of elements to insert into the heap
        System.out.println("Enter the number of elements to insert into the heap:");
        int numElements = scanner.nextInt(); // Read the number of elements

        // Prompt the user to enter the elements to be inserted
        System.out.println("Enter the elements:");
        for (int i = 0; i < numElements; i++) { // Loop through the number of elements
            insert(scanner.nextInt()); // Insert the element into the k-ary heap
        }

        // Display the heap after insertion
        System.out.println("Heap after insertion: " + arl); // Print the ArrayList representing the heap
        // Display the maximum element (the top element in a max-heap)
        System.out.println("Get Top element: " + getMax()); // Print the maximum element

        // Remove the maximum element from the heap
        removeMax(); // Call the removeMax function to remove the top element
        // Display the heap after removal of the maximum element
        System.out.println("Heap after removing the maximum element: " + arl); // Print the updated heap
        // Display the new maximum element after the removal
        System.out.println("The maximum element in the heap is now: " + getMax()); // Print the new top element

        scanner.close(); // Close the Scanner to free up resources
    }
}
