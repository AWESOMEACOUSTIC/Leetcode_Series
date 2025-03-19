import java.util.*;

public class bellman_ford {
    // Edge class representing a directed edge with a source, destination, and weight
    static class Edge {
        int src, dest, weight; // Source vertex, destination vertex, and weight of the edge

        // Constructor to initialize an edge with source, destination, and weight
        Edge(int src, int dest, int weight) {
            this.src = src;     // Set source vertex
            this.dest = dest;   // Set destination vertex
            this.weight = weight; // Set weight of the edge
        }
    }

    public static void main(String[] args) { // Main method
        Scanner sc = new Scanner(System.in); // Create a Scanner object for user input

        // Read the number of vertices
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt(); // Number of vertices in the graph

        // Read the number of edges
        System.out.print("Enter number of edges: ");
        int E = sc.nextInt(); // Number of edges in the graph

        // Create an array to store all edges
        Edge[] edges = new Edge[E]; // Array of edges

        // Read each edge from the user
        System.out.println("Enter " + E + " edges in the format: source destination weight");
        for (int i = 0; i < E; i++) { // Loop over each edge
            int u = sc.nextInt();     // Read the source vertex of the edge
            int v = sc.nextInt();     // Read the destination vertex of the edge
            int w = sc.nextInt();     // Read the weight of the edge
            edges[i] = new Edge(u, v, w); // Create a new Edge and store it in the array
        }

        // Read the source vertex for the shortest path calculation
        System.out.print("Enter source vertex: ");
        int src = sc.nextInt(); // Source vertex

        // Initialize distance array to store shortest path estimates
        int INF = Integer.MAX_VALUE;
        int[] dist = new int[V]; // Distance array for each vertex
        Arrays.fill(dist, INF); // Initialize all distances to "infinity"
        dist[src] = 0; // Distance from source to itself is 0

        // Relax all edges V-1 times (main loop of Bellman-Ford)
        for (int i = 1; i < V; i++) { // Repeat V-1 times
            for (int j = 0; j < E; j++) { // For each edge
                int u = edges[j].src; // Get source vertex of the edge
                int v = edges[j].dest; // Get destination vertex of the edge
                int w = edges[j].weight; // Get weight of the edge
                // If the source vertex u is reachable and can provide a shorter path to v
                if (dist[u] != INF && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        // Check for negative weight cycles by trying one more relaxation
        boolean hasNegativeCycle = false; // Flag to check negative cycle
        for (int j = 0; j < E; j++) { // For each edge
            int u = edges[j].src; // Get source vertex
            int v = edges[j].dest; // Get destination vertex
            int w = edges[j].weight; // Get weight of the edge
            // If we can still relax an edge, a negative cycle exists
            if (dist[u] != INF && dist[u] + w < dist[v]) {
                hasNegativeCycle = true; // Negative cycle found
                break; // No need to check further, break out of the loop
            }
        }

        // Print the results: either an error message or the shortest distances
        if (hasNegativeCycle) {
            System.out.println("Graph contains a negative weight cycle");
        } else {
            System.out.println("Shortest distances from vertex " + src + ":");
            for (int i = 0; i < V; i++) { // For each vertex
                // Print the distance to each vertex; if unreachable, print "INF"
                System.out.println("To vertex " + i + " : " + (dist[i] == INF ? "INF" : dist[i]));
            }
        }
        sc.close();
    }
}
