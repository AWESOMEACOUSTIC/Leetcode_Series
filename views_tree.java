import java.util.*; // Import utility classes for Scanner, ArrayList, Queue, etc.

public class views_tree { // Define the main class "Views"

    // Definition for a binary tree node.
    static class TreeNode {
        int val;           // Value stored in the node
        TreeNode left, right; // References to the left and right child nodes

        // Constructor to create a new tree node with the given value.
        TreeNode(int val) {
            this.val = val;         // Set the node's value
            this.left = this.right = null; // Initialize left and right children as null
        }
    }

    // Helper class to store a tree node along with its horizontal distance (hd).
    static class Pair {
        TreeNode node; // The tree node
        int hd;        // Horizontal distance from the root

        // Constructor to initialize a Pair with a node and its horizontal distance.
        Pair(TreeNode node, int hd) {
            this.node = node; // Set the node
            this.hd = hd;     // Set the horizontal distance
        }
    }

    // Method to compute the Top View of the binary tree.
    public static List<Integer> topView(TreeNode root) {
        List<Integer> result = new ArrayList<>(); // List to store the top view values.
        if (root == null)
            return result; // Return empty list if tree is empty.

        Map<Integer, Integer> map = new TreeMap<>(); // TreeMap to store the first node for each horizontal distance.
        Queue<Pair> queue = new LinkedList<>();       // Queue to perform level-order traversal (BFS).
        queue.add(new Pair(root, 0));                   // Enqueue the root node with horizontal distance 0.

        while (!queue.isEmpty()) {                      // While there are nodes to process...
            Pair current = queue.poll();              // Dequeue the front element.
            TreeNode node = current.node;             // Get the current node.
            int hd = current.hd;                      // Get its horizontal distance.

            // If this horizontal distance is not already in the map, add the node's value.
            if (!map.containsKey(hd)) {
                map.put(hd, node.val);
            }

            // Enqueue left child with horizontal distance hd - 1.
            if (node.left != null)
                queue.add(new Pair(node.left, hd - 1));
            // Enqueue right child with horizontal distance hd + 1.
            if (node.right != null)
                queue.add(new Pair(node.right, hd + 1));
        }

        result.addAll(map.values()); // Add all values from the map (ordered by hd) to the result list.
        return result;
    }

    // Method to compute the Bottom View of the binary tree.
    public static List<Integer> bottomView(TreeNode root) {
        List<Integer> result = new ArrayList<>(); // List to store the bottom view values.
        if (root == null)
            return result; // Return empty list if tree is empty.

        Map<Integer, Integer> map = new TreeMap<>(); // TreeMap to store the last node for each horizontal distance.
        Queue<Pair> queue = new LinkedList<>();       // Queue for level-order traversal.
        queue.add(new Pair(root, 0));                   // Enqueue the root with hd 0.

        while (!queue.isEmpty()) {
            Pair current = queue.poll();              // Dequeue the front element.
            TreeNode node = current.node;             // Get the current node.
            int hd = current.hd;                      // Get its horizontal distance.

            map.put(hd, node.val); // Update the map so that the last node at this hd is stored.

            // Enqueue left child with hd - 1.
            if (node.left != null)
                queue.add(new Pair(node.left, hd - 1));
            // Enqueue right child with hd + 1.
            if (node.right != null)
                queue.add(new Pair(node.right, hd + 1));
        }

        result.addAll(map.values()); // Add all bottom view values (ordered by hd) to the result list.
        return result;               // Return the bottom view.
    }

    // Method to compute the Left View of the binary tree.
    public static List<Integer> leftView(TreeNode root) {
        List<Integer> result = new ArrayList<>(); // List to store the left view values.
        if (root == null)
            return result; // Return empty list if tree is empty.

        Queue<TreeNode> queue = new LinkedList<>(); // Queue for level-order traversal.
        queue.add(root); // Enqueue the root.

        // Process each level of the tree.
        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.size(); i++) {
                TreeNode node = queue.poll(); // Dequeue a node.
                // For left view, record the first node of each level.
                if (i == 0) {
                    result.add(node.val);
                }
                // Enqueue left child if it exists.
                if (node.left != null)
                    queue.add(node.left);
                // Enqueue right child if it exists.
                if (node.right != null)
                    queue.add(node.right);
            }
        }
        return result; // Return the left view.
    }

    // Method to compute the Right View of the binary tree.
    public static List<Integer> rightView(TreeNode root) {
        List<Integer> result = new ArrayList<>(); // List to store the right view values.
        if (root == null)
            return result; // Return empty list if tree is empty.

        Queue<TreeNode> queue = new LinkedList<>(); // Queue for level-order traversal.
        queue.add(root); // Enqueue the root.

        // Process each level of the tree.
        while (!queue.isEmpty()) {
            int size = queue.size(); // Number of nodes at the current level.
            // Process nodes at the current level.
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll(); // Dequeue a node.
                // For right view, record the last node at each level.
                if (i == size - 1) {
                    result.add(node.val);
                }
                // Enqueue left child if it exists.
                if (node.left != null)
                    queue.add(node.left);
                // Enqueue right child if it exists.
                if (node.right != null)
                    queue.add(node.right);
            }
        }
        return result; // Return the right view.
    }

    // Method to build a binary tree from a list of Integer values in level order.
    // "null" values represent missing nodes.
    public static TreeNode buildTree(List<Integer> arr) {
        // If the list is empty or the first element is null, return null (empty tree).
        if (arr == null || arr.isEmpty() || arr.get(0) == null)
            return null;

        Queue<TreeNode> queue = new LinkedList<>(); // Queue for level order tree construction.
        TreeNode root = new TreeNode(arr.get(0));     // Create the root node.
        queue.add(root); // Enqueue the root.

        int index = 1; // Start index for subsequent elements.
        while (!queue.isEmpty() && index < arr.size()) { // Loop until queue is empty or all elements are processed.
            TreeNode current = queue.poll(); // Dequeue a node.

            // Process the left child.
            if (index < arr.size() && arr.get(index) != null) {
                current.left = new TreeNode(arr.get(index)); // Create left child.
                queue.add(current.left); // Enqueue left child.
            }
            index++; // Move to the next element.

            // Process the right child.
            if (index < arr.size() && arr.get(index) != null) {
                current.right = new TreeNode(arr.get(index)); // Create right child.
                queue.add(current.right); // Enqueue right child.
            }
            index++; // Move to the next element.
        }
        return root; // Return the root of the constructed tree.
    }

    // Main method: program entry point.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object for user input.

        // Prompt the user to input tree nodes in level order (using "null" for missing nodes).
        System.out.println("Enter tree nodes in level order (use 'null' for missing nodes):");
        String input = scanner.nextLine().trim(); // Read the entire input line and trim whitespace.

        // Parse the input into a list of Integer values.
        List<Integer> nodes = new ArrayList<>();
        for (String part : input.split("\\s+")) { // Split the input by whitespace.
            if (part.equalsIgnoreCase("null")) { // If the token is "null",
                nodes.add(null); // add null to the list.
            } else {
                nodes.add(Integer.parseInt(part)); // Otherwise, parse the integer and add it.
            }
        }

        // Build the binary tree using the provided list.
        TreeNode root = buildTree(nodes);
        if (root == null) { // If the tree is empty, inform the user and exit.
            System.out.println("The tree is empty.");
            return;
        }

        // Print the different views of the binary tree.
        System.out.println("Top View: " + topView(root));       // Print the top view.
        System.out.println("Bottom View: " + bottomView(root));   // Print the bottom view.
        System.out.println("Left View: " + leftView(root));       // Print the left view.
        System.out.println("Right View: " + rightView(root));     // Print the right view.

        scanner.close(); // Close the scanner to free resources.
    }
}
