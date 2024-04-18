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
    private MinHeap pq;

    public Prim(Graph graph) {
        this.graph = graph;
        int vertices = graph.vertices;
        this.D = new double[vertices+1];
        this.edgeTo = new Edge[vertices+1];
        this.inMST = new boolean[vertices+1];
        this.pq = new MinHeap(vertices);
    }

    public List<Edge> primsMST() {

        // Initialize the D values and priority queue
        Arrays.fill(D, Double.POSITIVE_INFINITY);
        D[0] = 0;  // Make key of first vertex as 0

        int[] DInt = Arrays.stream(D).mapToInt(i -> (int) i).toArray();
        pq.heap_ini(DInt, graph.vertices);


        // While the priority queue is not empty
        while (pq.getN() != 0) {  // Updated this line
            // Extract the minimum key vertex
            int u = pq.min_id()-1;
            pq.delete_min();

            // Include u to MST
            inMST[u] = true;

            // For each adjacent vertex v of u
            for (Edge edge : graph.adjList.get(u)) {
                int v = edge.dest;
                double weight = edge.weight;
                //print u and v
                System.out.println("u: " + u + " v: " + v);
                // If v is not in MST and weight of (u, v) is smaller than D value of v
                if (!inMST[v] && weight < D[v]) {
                    // Store u in edgeTo array
                    edgeTo[v] = edge;

                    // Update the D value of v
                    D[v] = weight;

                    // Decrease key of v in the priority queue
                    if (pq.in_heap(v + 1)) {
                        pq.decrease_key(v + 1, (int) weight);
                    }
                }
            }
        }

        // Build the result list from the edgeTo array
        return new ArrayList<>(Arrays.asList(edgeTo).subList(1, graph.vertices));
    }
}