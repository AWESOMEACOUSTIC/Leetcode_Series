import java.util.*; // Import utility classes (Scanner, ArrayList, LinkedList, etc.)

public class BFS_DFS {
    // BFS using a queue. This method traverses the graph level by level starting from the 'start' node.
    static void bfs(int start, List<List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()]; // Array to track whether each vertex has been visited
        Queue<Integer> q = new LinkedList<>();         // Queue to hold the nodes to visit next
        visited[start] = true; // Mark the starting node as visited
        q.add(start);          // Enqueue the starting node

        while (!q.isEmpty()) {               // Continue until there are no nodes left in the queue
            int node = q.poll();             // Dequeue the front node from the queue
            System.out.print(node + " ");    // Print the current node

            // Iterate over all neighbors of the current node
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {    // If the neighbor hasn't been visited yet,
                    visited[neighbor] = true; // mark it as visited,
                    q.add(neighbor);         // and add it to the queue.
                }
            }
        }
    }

    // Global visited array used by the DFS method.
    static boolean[] visited;

    // DFS using recursion. This method explores as far as possible along each branch before backtracking.
    static void dfs(int node, List<List<Integer>> graph) {
        visited[node] = true;              // Mark the current node as visited
        System.out.print(node + " ");      // Print the current node

        // Iterate over all neighbors of the current node
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {      // If the neighbor has not been visited,
                dfs(neighbor, graph);      // recursively perform DFS on that neighbor.
            }
        }
    }

    // Main method: Entry point of the program.
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Create a Scanner object to read user input

        // Prompt the user to input the number of vertices in the graph.
        System.out.print("Enter number of vertices: ");
        int vertices = sc.nextInt();         // Read the number of vertices

        // Prompt the user to input the number of edges in the graph.
        System.out.print("Enter number of edges: ");
        int edges = sc.nextInt();            // Read the number of edges

        // Create an adjacency list to represent the graph.
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());    // Initialize an empty list for each vertex
        }

        // Inform the user to enter the edge details.
        System.out.println("Enter " + edges + " edges in format: source destination");
        for (int i = 0; i < edges; i++) {
            int u = sc.nextInt();  // Read the source vertex of the edge
            int v = sc.nextInt();  // Read the destination vertex of the edge

            graph.get(u).add(v);   // Add an edge from u to v in the adjacency list

            // Uncomment the following line if you want the graph to be undirected:
            // graph.get(v).add(u);
        }

        // Prompt the user to enter the starting vertex for traversals.
        System.out.print("Enter starting vertex for traversal: ");
        int start = sc.nextInt(); // Read the starting vertex

        // Perform and print the BFS traversal starting from the given vertex.
        System.out.print("BFS from " + start + ": ");
        bfs(start, graph); // Call the BFS method with the starting vertex and graph
        System.out.println(); // Move to a new line after BFS output

        // Initialize the visited array for DFS using the number of vertices.
        visited = new boolean[vertices];

        // Perform and print the DFS traversal starting from the given vertex.
        System.out.print("DFS from " + start + ": ");
        dfs(start, graph); // Call the DFS method with the starting vertex and graph
        System.out.println(); // Move to a new line after DFS output

        sc.close(); // Close the scanner to free resources
    }
}
