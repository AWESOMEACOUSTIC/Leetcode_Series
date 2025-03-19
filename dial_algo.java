import java.util.*; // Import utility classes for data structures and user input

public class dial_algo {
    // Edge class representing an edge from one vertex to another with a given weight
    static class Edge {
        int to, weight; // Destination vertex and weight of the edge

        // Constructor to initialize an edge with destination and weight
        Edge(int to, int weight) {
            this.to = to;         // Set the destination vertex
            this.weight = weight; // Set the edge's weight
        }
    }

    // Dial's algorithm to compute shortest paths from source in a graph with nonnegative integer weights
    static int[] dial(int n, List<List<Edge>> graph, int source, int W) {
        int INF = Integer.MAX_VALUE;      // Define infinity as a large value
        int[] dist = new int[n];          // Array to store shortest distances from the source
        Arrays.fill(dist, INF);           // Initialize all distances to infinity
        dist[source] = 0;                 // Distance from source to itself is 0

        int maxDist = (n - 1) * W;          // Maximum possible distance (worst-case scenario)
        // Create buckets to group vertices by current distance; each bucket is a list of vertices
        List<List<Integer>> buckets = new ArrayList<>(maxDist + 1);
        for (int i = 0; i <= maxDist; i++) { // Initialize each bucket from 0 to maxDist
            buckets.add(new ArrayList<>());  // Add an empty list for each bucket
        }
        buckets.get(0).add(source);          // Place the source vertex in bucket 0

        int currentBucket = 0;               // Start processing from bucket 0
        // Process until all buckets up to maxDist have been checked
        while (currentBucket <= maxDist) {
            // If the current bucket is empty, move to the next bucket
            if (buckets.get(currentBucket).isEmpty()) {
                currentBucket++;           // Move to the next bucket
                continue;                  // Skip the rest of the loop iteration
            }
            // Remove a vertex from the current bucket
            int u = buckets.get(currentBucket).remove(buckets.get(currentBucket).size() - 1);
            // If the vertex's distance doesn't match the current bucket, skip it (it's outdated)
            if (dist[u] != currentBucket)
                continue;

            // Relax all edges from vertex u
            for (Edge edge : graph.get(u)) { // For each edge from u
                int v = edge.to, w = edge.weight; // Get destination vertex v and edge weight w
                // If a shorter path to v is found via u, update the distance
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;   // Update distance to v
                    // Add vertex v to its corresponding bucket if within range
                    if (dist[v] <= maxDist)
                        buckets.get(dist[v]).add(v);
                }
            }
        }
        return dist; // Return the array of shortest distances
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Create a Scanner object for user input

        // Read the number of vertices in the graph
        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();

        // Read the number of edges in the graph
        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();

        // Initialize the graph as an adjacency list (list of lists)
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>()); // For each vertex, create an empty list for its edges
        }

        int maxW = 0; // Variable to track the maximum edge weight among all edges
        System.out.println("Enter " + e + " edges in the format: source destination weight");
        // Read each edge from user input
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();    // Read the source vertex of the edge
            int v = sc.nextInt();    // Read the destination vertex of the edge
            int w = sc.nextInt();    // Read the weight of the edge
            graph.get(u).add(new Edge(v, w)); // Add an edge from u to v with weight w
            // If the graph is undirected, uncomment the following line:
            // graph.get(v).add(new Edge(u, w));
            maxW = Math.max(maxW, w); // Update the maximum edge weight if current weight is larger
        }

        // Read the source vertex for the shortest path calculation
        System.out.print("Enter source vertex: ");
        int source = sc.nextInt();

        // Compute shortest distances using Dial's algorithm, using maxW as the maximum edge weight
        int[] dist = dial(n, graph, source, maxW);

        // Print the shortest distances from the source vertex to each vertex
        System.out.println("Shortest distances from vertex " + source + ":");
        for (int i = 0; i < n; i++) {
            // If a vertex is unreachable, print "INF"; otherwise, print the computed distance
            System.out.println("To vertex " + i + " : " + (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
        }
        sc.close(); // Close the Scanner object to free resources
    }
}
