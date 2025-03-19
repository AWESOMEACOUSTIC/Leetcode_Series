import java.util.*; // Import utility classes

public class bf{
    // Edge class representing a directed edge with source, destination, and weight.
    static class Edge {
        int src, dest, weight; // Source vertex, destination vertex, and weight of the edge

        // Constructor to initialize an edge with source, destination, and weight.
        Edge(int src, int dest, int weight) {
            this.src = src;       // Set source vertex
            this.dest = dest;     // Set destination vertex
            this.weight = weight; // Set weight of the edge
        }
    }

    public static void main(String[] args) { // Main method
        Scanner sc = new Scanner(System.in); // Create a Scanner object for user input

        // Read the number of vertices
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt(); // Number of vertices in the graph

        // Initialize the adjacency matrix for the graph.
        // We assume that if there is no edge between two vertices, user enters 99999.
        int[][] matrix = new int[V][V];
        System.out.println("Enter the adjacency matrix (each row on a new line):");
        for (int i = 0; i < V; i++) {         // Loop over each row
            for (int j = 0; j < V; j++) {     // Loop over each column
                matrix[i][j] = sc.nextInt();  // Read the matrix element at (i, j)
            }
        }

        // Read the source vertex for shortest path calculation.
        System.out.print("Enter source vertex: ");
        int src = sc.nextInt(); // Source vertex

        // Convert the adjacency matrix into an edge list.
        // For each pair (i, j), if i != j and matrix[i][j] is not 99999, add an edge from i to j.
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                // Ignore self-loops and entries with 99999 (representing no edge)
                if (i != j && matrix[i][j] != 99999) {
                    edges.add(new Edge(i, j, matrix[i][j]));
                }
            }
        }

        // Initialize the distance array for each vertex.
        int INF = Integer.MAX_VALUE;        // Define infinity as the maximum integer value
        int[] dist = new int[V];              // Distance array to store shortest path estimates
        Arrays.fill(dist, INF);               // Set all distances to "infinity"
        dist[src] = 0;                        // Distance from source to itself is 0

        // Relax all edges V-1 times (main loop of Bellman-Ford)
        for (int i = 1; i < V; i++) {         // Repeat V-1 times
            for (Edge e : edges) {            // For each edge
                // If the source vertex of the edge is reachable and the edge gives a shorter path to v:
                if (dist[e.src] != INF && dist[e.src] + e.weight < dist[e.dest]) {
                    dist[e.dest] = dist[e.src] + e.weight; // Update the distance to vertex v
                }
            }
        }

        // Check for negative weight cycles by trying one more relaxation.
        boolean hasNegativeCycle = false;     // Flag to indicate if a negative cycle exists
        for (Edge e : edges) {                 // For each edge
            if (dist[e.src] != INF && dist[e.src] + e.weight < dist[e.dest]) {
                hasNegativeCycle = true;       // Negative cycle found
                break;                         // Break out of the loop
            }
        }

        // Print the results: either an error message or the shortest distances.
        if (hasNegativeCycle) {
            System.out.println("Graph contains a negative weight cycle.");
        } else {
            System.out.println("Shortest distances from vertex " + src + ":");
            for (int i = 0; i < V; i++) {     // For each vertex
                // Print the distance to vertex i; if unreachable, print "INF"
                System.out.println("To vertex " + i + " : " + (dist[i] == INF ? "INF" : dist[i]));
            }
        }

        sc.close(); // Close the Scanner
    }
}
