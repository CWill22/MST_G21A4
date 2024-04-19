import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;
import java.io.FileNotFoundException;



public class Main {
    private final static File graphFile = new File("src/graph.txt");
    public static void main(String[] args) throws FileNotFoundException {
        //he first line of the input file contains an integer indicating number of vertices of the input graph.
        //• Each of the remaining lines contains a triple ′′i j w′′ indicating an edge between vertex i and vertex j
        //with cost w.
        //• Vertex 1 can be considered as the root.
        //Test

        //note about potential error
        //the vertices in your graph.txt file start from 1, while in your Java code, array and list indices start from 0. This might cause off-by-one errors when you try to access the vertices in your adjacency list. You might need to adjust your code to account for this difference.

        //print starting message
        System.out.println("Starting to read file...");
        //start reading graph.txt file

        try{
        Scanner sc = new Scanner(graphFile);
        System.out.println("File found...");

        System.out.println("reading first line...");
        int n = sc.nextInt(); //put size in n
        System.out.println("Size of graph: " + n);
        Graph graph = new Graph(n); //create graph with size n
        System.out.println("Graph created...");
        while(sc.hasNext()){
            int src = sc.nextInt() -1; //source
            int dest = sc.nextInt() -1; //destination
            double weight = sc.nextDouble(); //weight
            System.out.println("adding edge from " + (src + 1) + " to " + (dest + 1) + " with weight " + weight + " to graph...");
            graph.addEdge(src, dest, weight); //add edge to graph
        }
        //print original graph
        System.out.println("Printing original graph...");
            printGraph(n, graph);
            //print heap using print function
        System.out.println("Printing heap using print function...");
        //graph.printGraph();

        // Create Prim object and find MST
        System.out.println("Creating Prim object...");
        Prim prim = new Prim(graph);
        List<Edge> mst = prim.primsMST();

        // Print MST
        System.out.println("Minimum Spanning Tree: ");
        for (Edge edge : mst) {
            if(edge != null){
                System.out.println((edge.src + 1) + " - " + (edge.dest + 1)+ " : " + edge.weight);
            } else System.out.println("Edge is null...");

        }
        //print graph
        System.out.println("Printing graph...");
            printGraph(n, graph);
            //print Heap
        System.out.println("Printing MST heap...");
        prim.pq.printHeap();



        // Test MinHeap
        System.out.println("Testing MinHeap...");
        MinHeap minHeap = new MinHeap(5);
        int[] keys = {5, 3, 2, 1, 4};
        minHeap.heap_ini(keys, 5);
        System.out.println("Printing heap...");
        minHeap.printHeap();

        System.out.println("Min key: " + minHeap.min_key());
        minHeap.delete_min();
        System.out.println("Min key after delete_min: " + minHeap.min_key());
        minHeap.decrease_key(4, 0);
        System.out.println("Min key after decrease_key: " + minHeap.min_key());
        System.out.println("Key of 3: " + minHeap.key(3));
        System.out.println("Min Key: " + minHeap.min_key());
        //print heap
        System.out.println("Printing heap...");
        minHeap.printHeap();




        //close file
        sc.close();
    }
    catch(FileNotFoundException e) {
        System.out.println("File not found...");
    }
    }

    private static void printGraph(int n, Graph graph) {
        for (int i = 0; i < n; i++) {
            System.out.print((i+1) + " -> ");
            for (Edge edge : graph.adjList.get(i)) {
                System.out.print((edge.dest + 1) + " ");
            }
            System.out.println();
        }
    }
}
