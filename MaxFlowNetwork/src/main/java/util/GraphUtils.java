package util;

import graph.Edge;
import graph.Vertex;

/**
 * Utility class for logging and visualizing graph algorithms.
 * Specifically used for logging the Ford-Fulkerson algorithm's execution.
 */
public class GraphUtils {
      /**
     * Logs the augmenting path found during Ford-Fulkerson algorithm execution.
     * Displays the path from source to sink with flow and capacity details for each edge,
     * and the bottleneck capacity.
     *
     * @param edgeTo Array of edges that forms the augmenting path
     * @param s Source vertex
     * @param t Sink vertex
     */    public static void logAugmentingPath(Edge[] edgeTo, Vertex s, Vertex t) {
        // Initialize a string builder to construct the path
        StringBuilder pathStr = new StringBuilder();
        
        // Start with the sink vertex
        Vertex current = t;
        double bottleneck = Double.POSITIVE_INFINITY;
        
        // Start with source vertex ID
        pathStr.append("Augmenting Path: " + s.getId());
        
        // Traverse backward from sink to source to collect edges
        Vertex[] pathVertices = new Vertex[edgeTo.length]; // Store vertices in path
        Edge[] pathEdges = new Edge[edgeTo.length];       // Store edges in path
        double[] residualCapacities = new double[edgeTo.length]; // Store residual capacities
        int pathLength = 0;
        
        while (current != s) {
            // Get the edge connecting to the current vertex
            Edge e = edgeTo[current.getId()];
            
            // Find the other end of this edge (the previous vertex in the path)
            Vertex prev = e.getOtherVertex(current);
            
            // Calculate residual capacity for this edge from current vertex
            double residualCapacity = e.getResidualCapacity(current);
            
            // Store the vertex, edge, and residual capacity for later formatting
            pathVertices[pathLength] = current;
            pathEdges[pathLength] = e;
            residualCapacities[pathLength] = residualCapacity;
            pathLength++;
            
            // Update bottleneck capacity if this edge has smaller residual capacity
            if (residualCapacity < bottleneck) {
                bottleneck = residualCapacity;
            }
            
            // Move to the previous vertex
            current = prev;
        }
        
        // Add edges in correct order (from source to sink)
        for (int i = pathLength - 1; i >= 0; i--) {
            Vertex to = pathVertices[i];
            double residualCapacity = residualCapacities[i];
            
            // Format: VertexTo : [residualCapacity]
            pathStr.append(" -> " + to.getId() + " : [" + residualCapacity + "]");
        }
        
        // Print the augmenting path and the bottleneck capacity
        System.out.println(pathStr.toString());
        System.out.println("Bottleneck capacity: " + bottleneck);
        System.out.println("-------------------------------------\n");
    }
    
    /**
     * Logs the final maximum flow calculated by the Ford-Fulkerson algorithm.
     *
     * @param maxFlow The maximum flow value
     */
    public static void logMaxFlow(double maxFlow) {
        System.out.println("=== MAX FLOW CALCULATED ===");
        System.out.println("Total Maximum Flow: " + maxFlow);
    }
}
