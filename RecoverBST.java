import java.util.*;

public class RecoverBST {

    public static class TreeNode {
        int val;
        TreeNode left, right;


        public TreeNode(int value) {
            this.val = value;
            this.left = this.right = null;
        }
    }

    public static TreeNode buildTree(List<Integer> arr) {
        if (arr == null || arr.isEmpty() || arr.get(0) == null)
            return null;

        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(arr.get(0));
        queue.add(root);
        int index = 1; // Start with the next element in the list
        while (!queue.isEmpty() && index < arr.size()) {
            TreeNode current = queue.poll();

            if (index < arr.size() && arr.get(index) != null) {
                current.left = new TreeNode(arr.get(index));
                queue.add(current.left);
            }
            index++;

            if (index < arr.size() && arr.get(index) != null) {
                current.right = new TreeNode(arr.get(index));
                queue.add(current.right);
            }
            index++;
        }
        return root;
    }

    public static void inorderTraversal(TreeNode root, List<TreeNode> node) {
        if (root == null)
            return;
        inorderTraversal(root.left, node);
        node.add(root);
        inorderTraversal(root.right, node);
    }

    public static void recoverBST(TreeNode root) {
        List<TreeNode> nodeList = new ArrayList<>();
        inorderTraversal(root, nodeList);

        List<Integer> values = new ArrayList<>();
        for (TreeNode node : nodeList) {
            values.add(node.val);
        }

        // Sort the values. This will be the correct inorder (sorted) order for a BST.
        Collections.sort(values);

        // Reassign the sorted values back to the nodes (in inorder order)
        for (int i = 0; i < nodeList.size(); i++) {
            nodeList.get(i).val = values.get(i);
        }
    }

    public static void printInorder(TreeNode root) {
        if (root == null)
            return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter tree nodes in level order (use 'null' for empty nodes):");
        String input = scanner.nextLine().trim();

        List<Integer> nodes = new ArrayList<>();
        for (String part : input.split("\\s+")) {
            if (part.equalsIgnoreCase("null")) {
                nodes.add(null);
            } else {
                nodes.add(Integer.parseInt(part));
            }
        }

        TreeNode root = buildTree(nodes);
        if (root == null) {
            System.out.println("The tree is empty.");
            return;
        }

        System.out.println("Inorder traversal before recovery:");
        printInorder(root);
        System.out.println();

        recoverBST(root);

        System.out.println("Inorder traversal after recovery (should be sorted):");
        printInorder(root);
        System.out.println();

        scanner.close();
    }
}
