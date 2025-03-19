import java.util.*; // Import utility classes

public class bountra { // Define the WinnerTree class (or any class name you prefer)

    // Inner class representing a node in the binary tree
    static class TreeNode {
        int val;            // The value stored in the node
        TreeNode left, right; // Pointers to left and right child nodes

        public TreeNode(int value) { // Constructor for TreeNode
            val = value;         // Set the node's value
            this.left = this.right = null; // Initialize children to null
        }
    }

    // Method to collect left boundary nodes in top-down order (excluding leaves)
    static void collectLeftBoundary(TreeNode node, List<Integer> result) {
        // If node is null or is a leaf node, return without adding it
        if (node == null || (node.left == null && node.right == null))
            return;
        result.add(node.val); // Add current node's value to the result list
        // Traverse to the next boundary node: prefer left child, else right child
        if (node.left != null) {
            collectLeftBoundary(node.left, result);
        } else if (node.right != null) {
            collectLeftBoundary(node.right, result);
        }
    }

    // Method to collect all leaf nodes in left-to-right order
    static void collectLeaves(TreeNode node, List<Integer> result) {
        if (node == null)
            return; // Base case: nothing to do if node is null
        // If node is a leaf, add its value
        if (node.left == null && node.right == null) {
            result.add(node.val);
            return;
        }
        // Recursively collect leaves from left and right subtrees
        collectLeaves(node.left, result);
        collectLeaves(node.right, result);
    }

    // Method to collect right boundary nodes in bottom-up order (excluding leaves)
    // Instead of using recursion to add at the end, we add each node at index 0,
    // which effectively reverses the order.
    static void collectRightBoundary(TreeNode node, List<Integer> result) {
        // If node is null or a leaf, return without adding it
        if (node == null || (node.left == null && node.right == null))
            return;
        // Traverse to the next boundary node: prefer right child, else left child
        if (node.right != null) {
            collectRightBoundary(node.right, result);
        } else if (node.left != null) {
            collectRightBoundary(node.left, result);
        }
        result.add(0, node.val); // Add current node's value at the beginning to reverse the order
    }

    // Method to perform the complete boundary traversal of a binary tree
    public static List<Integer> boundaryTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>(); // List to store the boundary traversal
        if (root == null)
            return result; // Return empty list if tree is empty

        result.add(root.val); // Add the root's value

        // Collect left boundary nodes (starting from root.left)
        collectLeftBoundary(root.left, result);

        // Collect all leaf nodes from the left and right subtrees
        collectLeaves(root.left, result);
        collectLeaves(root.right, result);

        // Collect right boundary nodes (starting from root.right) into a temporary list in reverse order
        List<Integer> rightBoundary = new ArrayList<>();
        collectRightBoundary(root.right, rightBoundary);
        result.addAll(rightBoundary); // Append right boundary nodes to the result list

        return result; // Return the complete boundary traversal list
    }

    // Method to build a binary tree from a list of integers representing a level order traversal.
    // "null" values are represented by Java null to indicate missing nodes.
    public static TreeNode buildTree(List<Integer> arr) {
        if (arr == null || arr.isEmpty() || arr.get(0) == null)
            return null; // If the input is empty or starts with null, return an empty tree

        Queue<TreeNode> queue = new LinkedList<>(); // Queue for level order tree construction
        TreeNode root = new TreeNode(arr.get(0)); // Create the root node
        queue.add(root); // Enqueue the root

        int index = 1; // Start with the next element in the list
        while (!queue.isEmpty() && index < arr.size()) { // Loop until all elements are processed
            TreeNode current = queue.poll(); // Dequeue the next node

            // Process left child
            if (index < arr.size() && arr.get(index) != null) {
                current.left = new TreeNode(arr.get(index));
                queue.add(current.left);
            }
            index++; // Move to the next element

            // Process right child
            if (index < arr.size() && arr.get(index) != null) {
                current.right = new TreeNode(arr.get(index));
                queue.add(current.right);
            }
            index++; // Move to the next element
        }
        return root; // Return the root of the constructed tree
    }

    // Main method: entry point of the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a Scanner for user input

        // Prompt the user to input tree nodes in level order, using "null" for missing nodes
        System.out.println("Enter tree nodes in level order (use 'null' for empty nodes):");
        String input = scanner.nextLine().trim(); // Read the entire input line and trim extra spaces

        List<Integer> nodes = new ArrayList<>(); // List to store parsed node values
        // Split the input string by whitespace and parse each part
        for (String part : input.split("\\s+")) {
            if (part.equalsIgnoreCase("null")) { // If the token is "null"
                nodes.add(null); // Add a null entry
            } else {
                nodes.add(Integer.parseInt(part)); // Otherwise, parse and add the integer value
            }
        }

        // Build the binary tree from the list of node values
        TreeNode root = buildTree(nodes);
        if (root == null) { // If the tree is empty, exit the program
            return;
        }

        // Perform the boundary traversal on the constructed tree
        List<Integer> result = boundaryTraversal(root);
        // Print the boundary traversal result
        System.out.println("Boundary traversal: " + result);

        scanner.close(); // Close the scanner to free up resources
    }
}
