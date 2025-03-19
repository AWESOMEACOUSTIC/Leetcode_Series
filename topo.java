import java.util.*; // Import utility classes (Scanner, ArrayList, Stack, etc.)

public class topo { // Define the main class "topologicalSort"
    public static void main(String[] args) { // Main method: program entry point
        Scanner scanner = new Scanner(System.in); // Create a Scanner object for user input

        // Prompt and read the number of vertices in the graph
        System.out.println("Enter the number of vertices:");
        int V = scanner.nextInt(); // Store the number of vertices

        // Prompt and read the adjacency matrix
        System.out.println("Enter the adjacency matrix (each row on a new line):");
        int[][] matrix = new int[V][V]; // Create a 2D array for the matrix
        for (int i = 0; i < V; i++) { // Loop over each row
            for (int j = 0; j < V; j++) { // Loop over each column
                matrix[i][j] = scanner.nextInt(); // Read the matrix element at (i, j)
            }
        }

        // Convert the adjacency matrix into an adjacency list representation
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) { // Loop over each vertex
            adj.add(new ArrayList<>()); // Add an empty list for vertex i
            for (int j = 0; j < V; j++) { // Loop over each column in the row
                // If matrix[i][j] is non-zero, an edge from i to j exists
                if (matrix[i][j] != 0) {
                    adj.get(i).add(j); // Add vertex j to the adjacency list of vertex i
                }
            }
        }

        // Call the topoSort function to get the topological order of vertices
        int[] result = topoSort(V, adj);

        // Print the topological sort order
        System.out.println("Topological Sort Order:");
        for (int vertex : result) { // Iterate over the sorted vertices
            System.out.print(vertex + " "); // Print each vertex followed by a space
        }
        System.out.println(); // Move to a new line after printing

        scanner.close(); // Close the Scanner to free resources
    }

    // Function to perform Topological Sort using DFS and return the order as an array
    private static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V]; // Boolean array to track visited vertices
        Stack<Integer> stack = new Stack<>(); // Stack to store vertices in finishing order

        // Perform DFS for all vertices that haven't been visited
        for (int i = 0; i < V; i++) { // Loop over all vertices
            if (!visited[i]) { // If vertex i is not visited
                dfs(i, adj, visited, stack); // Call DFS starting from vertex i
            }
        }

        // Create an array to hold the topological order
        int[] topoOrder = new int[V]; // Array to store final topological order
        int index = 0; // Initialize index for insertion

        // Pop vertices from the stack to form the topological order
        while (!stack.isEmpty()) { // While stack is not empty
            topoOrder[index++] = stack.pop(); // Pop from stack and store in the array
        }

        return topoOrder; // Return the topological order array
    }

    // Helper function to perform DFS starting from a given node
    private static void dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true; // Mark the current node as visited

        // Recursively visit all unvisited neighbors of the current node
        for (int neighbor : adj.get(node)) { // Loop over each neighbor
            if (!visited[neighbor]) { // If the neighbor is not visited
                dfs(neighbor, adj, visited, stack); // Recursively call DFS on the neighbor
            }
        }

        // After visiting all neighbors, push the current node onto the stack
        stack.push(node); // Push node to stack (this ensures finishing order)
    }
}
