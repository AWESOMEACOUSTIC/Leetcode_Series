import java.util.*;

import java.util.Scanner;

public class winner_tree { // Define the WinnerTree class
    static int[] tree, leaves; // Declare static arrays: 'tree' for the winner tree and 'leaves' for the input leaves
    static int n; // Declare static integer 'n' to represent the number of leaves

    // Method to build the winner tree from the leaves
    static void build() {
        // Loop from the last internal node (index n - 1) down to the root (index 1)
        for (int i = n - 1; i > 0; i--)
            // Set the current internal node as the minimum of its two children
            tree[i] = Math.min(tree[i * 2], tree[i * 2 + 1]);
    }

    // Method to retrieve the winner (minimum element) from the tree
    static int getWinner() {
        return tree[1]; // The winner is stored at the root of the tree (index 1)
    }

    public static void main(String[] args) { // Main method: program entry point
        Scanner sc = new Scanner(System.in); // Create a Scanner object for user input
        n = sc.nextInt(); // Read the number of leaves from the user and assign it to 'n'
        leaves = new int[n]; // Initialize the 'leaves' array with 'n' elements
        // Loop to read each leaf value from the user
        for (int i = 0; i < n; i++)
            leaves[i] = sc.nextInt(); // Read each integer and store it in the 'leaves' array

        tree = new int[2 * n]; // Initialize the 'tree' array with a size of 2*n (complete binary tree structure)
        System.arraycopy(leaves, 0, tree, n, n); // Copy the 'leaves' array into the second half of the 'tree' array
        build(); // Call the build method to construct the winner tree using the copied leaves

        System.out.println("Winner: " + getWinner()); // Print the winner (minimum element) from the winner tree

        sc.close(); // Close the Scanner to free up resources
    }
}
