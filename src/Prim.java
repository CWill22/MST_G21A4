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
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int src, int dest, double weight) {
        Edge edge = new Edge(src, dest, weight);
        adjList.get(src).add(edge);
    }
}
public class Prim {
    private Graph graph;
    private double[] D;
    private Edge[] edgeTo;
    private boolean[] inMST;
    private PriorityQueue<Integer> pq;

    public Prim(Graph graph) {
        this.graph = graph;
        int vertices = graph.vertices;
        this.D = new double[vertices];
        this.edgeTo = new Edge[vertices];
        this.inMST = new boolean[vertices];
        this.pq = new PriorityQueue<>(vertices, Comparator.comparingDouble(i -> D[i]));
    }

    public List<Edge> primsMST() {
        List<Edge> result = new ArrayList<>();

        // Initialize the D values and priority queue
        Arrays.fill(D, Double.POSITIVE_INFINITY);
        D[0] = 0;  // Make key of first vertex as 0
        pq.offer(0);

        // While the priority queue is not empty
        while (!pq.isEmpty()) {
            // Extract the minimum key vertex
            int u = pq.poll();

            // Include u to MST
            inMST[u] = true;

            // For each adjacent vertex v of u
            for (Edge edge : graph.adjList.get(u)) {
                int v = edge.dest;
                double weight = edge.weight;

                // If v is not in MST and weight of (u, v) is smaller than D value of v
                if (!inMST[v] && weight < D[v]) {
                    // Store u in edgeTo array
                    edgeTo[v] = edge;

                    // Update the D value of v
                    D[v] = weight;

                    // Add v to the priority queue
                    pq.offer(v);
                }
            }
        }

        // Build the result list from the edgeTo array
        for (int i = 1; i < graph.vertices; i++) {
            result.add(edgeTo[i]);
        }

        return result;
    }
}