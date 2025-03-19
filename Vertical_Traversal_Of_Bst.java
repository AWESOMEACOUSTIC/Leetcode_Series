import java.util.*;

public class Vertical_Traversal_Of_Bst {
    // Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    // Helper Tuple class to store a node along with its row and column values.
    class Tuple {
        TreeNode node;
        int row;
        int col;

        public Tuple(TreeNode node, int row, int col) {
            this.node = node;
            this.row = row;
            this.col = col;
        }
    }

    // Performs vertical traversal of the binary tree.
    // The structure is: column -> (row -> PriorityQueue of node values)
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // TreeMap to maintain sorted order of columns.
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        // Queue for BFS traversal.
        Queue<Tuple> q = new LinkedList<>();
        q.offer(new Tuple(root, 0, 0));

        while (!q.isEmpty()) {
            Tuple tuple = q.poll();
            TreeNode node = tuple.node;
            int row = tuple.row;
            int col = tuple.col;

            // If no mapping for this column exists, create one.
            if (!map.containsKey(col)) {
                map.put(col, new TreeMap<>());
            }
            // If no mapping for this row in the current column exists, create one.
            if (!map.get(col).containsKey(row)) {
                map.get(col).put(row, new PriorityQueue<>());
            }
            // Add the current node's value to the appropriate position.
            map.get(col).get(row).offer(node.val);

            // For the left child, row increases by 1 and column decreases by 1.
            if (node.left != null) {
                q.offer(new Tuple(node.left, row + 1, col - 1));
            }
            // For the right child, row increases by 1 and column increases by 1.
            if (node.right != null) {
                q.offer(new Tuple(node.right, row + 1, col + 1));
            }
        }

        // Build the final vertical order list from the map.
        List<List<Integer>> result = new ArrayList<>();
        for (TreeMap<Integer, PriorityQueue<Integer>> rows : map.values()) {
            List<Integer> colList = new ArrayList<>();
            for (PriorityQueue<Integer> nodes : rows.values()) {
                while (!nodes.isEmpty()) {
                    colList.add(nodes.poll());
                }
            }
            result.add(colList);
        }
        return result;
    }

    // Builds a binary tree from level order input.
    // The input should be space-separated values (e.g., "1 2 3 4 5 null 7")
    // Use "null" for missing nodes.
    public TreeNode buildTree(String input) {
        if (input.length() == 0)
            return null;
        String[] parts = input.split("\\s+");
        if (parts[0].equals("null") || parts[0].equals("None"))
            return null;

        TreeNode root = new TreeNode(Integer.parseInt(parts[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (!queue.isEmpty() && i < parts.length) {
            TreeNode curr = queue.poll();

            // Process left child.
            if (i < parts.length && !parts[i].equals("null")) {
                curr.left = new TreeNode(Integer.parseInt(parts[i]));
                queue.offer(curr.left);
            }
            i++;

            // Process right child.
            if (i < parts.length && !parts[i].equals("null")) {
                curr.right = new TreeNode(Integer.parseInt(parts[i]));
                queue.offer(curr.right);
            }
            i++;
        }
        return root;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Vertical_Traversal_Of_Bst sol = new Vertical_Traversal_Of_Bst();

        System.out.println("Enter tree nodes in level order separated by space (use 'null' for empty nodes):");
        String input = scanner.nextLine();

        TreeNode root = sol.buildTree(input);
        List<List<Integer>> verticalOrder = sol.verticalTraversal(root);

        System.out.println("Vertical Traversal:");
        for (List<Integer> col : verticalOrder) {
            System.out.println(col);
        }

        scanner.close();
    }
}

