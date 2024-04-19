import java.util.*;

class Edge {
    int src, dest;
    double weight;

    public Edge(int src, int dest, double weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

class Graph {
    int vertices;
    List<List<Edge>> adjList;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.adjList = new ArrayList<>(vertices);
        // Initialize the adjacency list.
        for (int i = 0; i < vertices+1; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int src, int dest, double weight) {
        Edge edge1 = new Edge(src, dest, weight);
        //Edge edge2 = new Edge(dest, src, weight); // Create a reverse edge for undirected graph
        adjList.get(src).add(edge1);
        //adjList.get(dest).add(edge2); // Add reverse edge to the adjacency list of destination vertex
    }

}

public class Prim {
    private Graph graph;
    private double[] D;
    private Edge[] edgeTo;
    private boolean[] inMST;
    public MinHeap pq;

    public Prim(Graph graph) {
        this.graph = graph;
        int vertices = graph.vertices;
        this.D = new double[vertices+1];  // Store the D value of each vertex
        this.edgeTo = new Edge[vertices+1]; // Store the edge of vertex v that gives the minimum weight to reach v from the MST
        this.inMST = new boolean[vertices+1]; // To keep track of vertices already included in MST
        this.pq = new MinHeap(vertices); // Priority queue to store vertices with minimum D value
    }

    public List<Edge> primsMST() {

        // Initialize the D values and priority queue
        Arrays.fill(D, Double.POSITIVE_INFINITY);
        D[0] = 0;  // Make key of first vertex as 0

        //int[] DInt = Arrays.stream(D).mapToInt(i -> (int) i).toArray();
        pq.heap_ini(D, graph.vertices);
        //System.out.println("Printing heap inside primsMST...");
        //pq.printHeap();

        // While the priority queue is not empty
        while (pq.getN() != 0) {  // Updated this line
            // Extract the minimum key vertex
            int u = pq.min_id();
            pq.delete_min();

            // Include u to MST
            inMST[u] = true;

            // For each adjacent vertex v of u
            for (Edge edge : graph.adjList.get(u)) {
                int v = edge.dest +1 ;
                double weight = edge.weight;
                //print u and v
                //System.out.println("u: " + (u) + " v: " + (v));
                // If weight of (u, v) is smaller than D value of v
                if (weight < D[v]) {
                    // Store u in edgeTo array
                    edgeTo[v] = edge;

                    // Update the D value of v
                    D[v] = weight;

                    // Decrease key of v in the priority queue
                    if (pq.in_heap(v )) {
                        pq.decrease_key(v , weight);
                    }
                }
            }
        }
// Build the result list from the edgeTo array
        List<Edge> result = new ArrayList<>();
        for (Edge edge : edgeTo) {
            if (edge != null) {
                result.add(edge);
            }
        }
        return result;
        // Build the result list from the edgeTo array
        //return new ArrayList<>(Arrays.asList(edgeTo).subList(1, graph.vertices));
    }
    public void printHeap(){
        pq.printHeap();
    }

}