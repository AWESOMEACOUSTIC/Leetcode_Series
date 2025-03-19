import java.util.*; // Import utility classes

public class da {

    // Edge class representing an edge from one vertex to another with a given weight
    static class Edge {
        int to, weight; // 'to' is the destination vertex, 'weight' is the weight of the edge

        // Constructor to initialize an edge with destination and weight
        Edge(int to, int weight) {
            this.to = to;       // Set destination vertex
            this.weight = weight; // Set edge weight
        }
    }

    // Dial's algorithm to compute shortest paths from source in a graph with nonnegative integer weights
    static int[] dial(int n, List<List<Edge>> graph, int source, int W) {
        int INF = Integer.MAX_VALUE;         // Define infinity as a large value
        int[] dist = new int[n];              // Array to store shortest distances from the source
        Arrays.fill(dist, INF);               // Initialize all distances to infinity
        dist[source] = 0;                     // Distance from source to itself is 0

        int maxDist = (n - 1) * W;             // Maximum possible distance (worst-case scenario)
        // Create buckets to group vertices by current distance; each bucket is a list of vertices
        List<List<Integer>> buckets = new ArrayList<>(maxDist + 1);
        for (int i = 0; i <= maxDist; i++) {   // Initialize each bucket from 0 to maxDist
            buckets.add(new ArrayList<>());
        }
        buckets.get(0).add(source);            // Place the source vertex in bucket 0
        int currentBucket = 0;                 // Start processing from bucket 0

        // Process each bucket until maxDist is reached
        while (currentBucket <= maxDist) {
            // If current bucket is empty, move to next bucket
            if (buckets.get(currentBucket).isEmpty()) {
                currentBucket++;
                continue;
            }
            // Remove a vertex from the current bucket
            int u = buckets.get(currentBucket).remove(buckets.get(currentBucket).size() - 1);
            // If the current known distance for u doesn't match currentBucket, skip (outdated entry)
            if (dist[u] != currentBucket)
                continue;

            // Relax all edges from vertex u
            for (Edge edge : graph.get(u)) {
                int v = edge.to, w = edge.weight; // Get destination and weight for edge from u
                // If a shorter path to v is found, update distance for v
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    // If the new distance is within the bucket range, add v to its corresponding bucket
                    if (dist[v] <= maxDist)
                        buckets.get(dist[v]).add(v);
                }
            }
        }
        return dist; // Return the array of shortest distances
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Create a Scanner for user input

        // Read number of vertices
        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();

        // Initialize the adjacency matrix for the graph.
        // We assume that if there is no edge, the user enters 99999.
        int[][] matrix = new int[n][n];
        System.out.println("Enter the adjacency matrix (each row on a new line):");
        for (int i = 0; i < n; i++) {            // Loop over each row
            for (int j = 0; j < n; j++) {        // Loop over each column
                matrix[i][j] = sc.nextInt();     // Read matrix element at (i, j)
            }
        }

        // Read the source vertex for shortest path calculation
        System.out.print("Enter source vertex: ");
        int source = sc.nextInt();

        // Convert the adjacency matrix into an edge list
        // For each pair (i, j), if i != j and matrix[i][j] != 99999, add an edge from i to j
        List<List<Edge>> graph = new ArrayList<>();
        // Initialize the graph as an adjacency list (list of lists)
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        int maxW = 0; // Variable to track the maximum edge weight among all edges
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Skip self loops and check for valid edge (assuming 99999 indicates no edge)
                if (i != j && matrix[i][j] != 99999) {
                    graph.get(i).add(new Edge(j, matrix[i][j])); // Add edge from vertex i to j
                    maxW = Math.max(maxW, matrix[i][j]);          // Update maxW if needed
                }
            }
        }

        // Compute shortest distances using Dial's algorithm with maxW as maximum edge weight
        int[] dist = dial(n, graph, source, maxW);

        // Print the shortest distances from the source to all vertices
        System.out.println("Shortest distances from vertex " + source + ":");
        for (int i = 0; i < n; i++) {
            // If vertex is unreachable, print "INF"; else print the computed distance
            System.out.println("To vertex " + i + " : " + (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
        }

        sc.close(); // Close the scanner
    }
}
