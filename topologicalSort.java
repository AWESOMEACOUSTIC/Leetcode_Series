import java.util.*; // Import the utility package for Scanner, ArrayList, Stack, etc.

public class topologicalSort { // Declare the TopologicalSort class
    public static void main(String[] args) { // Main method: entry point of the program
        Scanner scanner = new Scanner(System.in); // Create a Scanner object for user input

        // Prompt and read the number of vertices in the graph
        System.out.println("Enter the number of vertices:");
        int V = scanner.nextInt(); // Store the number of vertices in V

        // Prompt and read the number of edges in the graph
        System.out.println("Enter the number of edges:");
        int E = scanner.nextInt(); // Store the number of edges in E

        // Initialize an adjacency list for the graph
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {         // Loop over all vertices
            adj.add(new ArrayList<>());       // Add an empty list for each vertex
        }

        // Prompt and read the edges of the graph in "src dest" format
        System.out.println("Enter the edges in the format: src dest");
        for (int i = 0; i < E; i++) {         // Loop E times to read each edge
            int src = scanner.nextInt();      // Read the source vertex of the edge
            int dest = scanner.nextInt();     // Read the destination vertex of the edge
            adj.get(src).add(dest);           // Add the destination vertex to the source's adjacency list
        }

        // Call the topoSort function to get the topological order of the vertices
        int[] result = topoSort(V, adj);

        // Print the topological sort order
        System.out.println("Topological Sort Order:");
        for (int vertex : result) {           // Iterate over the result array
            System.out.print(vertex + " ");   // Print each vertex followed by a space
        }
        System.out.println();                 // Move to a new line

        scanner.close();                      // Close the Scanner object to free resources
    }

    // Function to perform Topological Sort using Depth-First Search (DFS)
    private static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V]; // Create a boolean array to keep track of visited vertices
        Stack<Integer> stack = new Stack<>(); // Create a stack to store the vertices in finishing order

        // Perform DFS for all vertices that have not been visited yet
        for (int i = 0; i < V; i++) {         // Loop over all vertices
            if (!visited[i]) {                // If vertex i has not been visited
                dfs(i, adj, visited, stack);  // Perform DFS starting from vertex i
            }
        }

        // Create an array to hold the topological order
        int[] topoOrder = new int[V];         // Array to store the final topological order
        int index = 0;                        // Initialize index for topoOrder array

        // Extract vertices from the stack to form the topological order
        while (!stack.isEmpty()) {            // While the stack is not empty
            topoOrder[index++] = stack.pop(); // Pop from stack and store in topoOrder array
        }

        return topoOrder;                     // Return the topological order array
    }

    // Helper function to perform DFS from a given node
    private static void dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;                 // Mark the current node as visited

        // Traverse all the neighbors of the current node
        for (int neighbor : adj.get(node)) {  // For each neighbor of the current node
            if (!visited[neighbor]) {         // If the neighbor hasn't been visited yet
                dfs(neighbor, adj, visited, stack); // Recursively call DFS on the neighbor
            }
        }

        // After visiting all neighbors, push the current node onto the stack
        stack.push(node);                     // Push the node onto the stack
    }
}
