package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a flow network consisting of vertices and edges.
 * Maintains the graph structure using an adjacency list representation.
 */
public class FlowNetwork {
    // Attributes
    private int numberOfVertices;
    private int numberOfEdges;
    private List<List<Edge>> adjacenciesList;
    private Map<Integer, Vertex> verticesMap; // Optional but useful for getVertexById
    
    /**
     * Constructs a flow network with the specified number of vertices
     * @param numberOfVertices the number of vertices in the network
     */
    public FlowNetwork(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
        this.numberOfEdges = 0;
        
        // Initialize adjacency list
        this.adjacenciesList = new ArrayList<>(numberOfVertices);
        for (int i = 0; i < numberOfVertices; i++) {
            List<Edge> edgeList = new ArrayList<>();
            adjacenciesList.add(edgeList);
        }
        
        // Initialize vertices map (optional)
        this.verticesMap = new HashMap<>();
    }
    
    /**
     * Gets the total number of vertices in the network
     * @return number of vertices
     */
    public int getNumberOfVertices() {
        return numberOfVertices;
    }
    
    /**
     * Gets the total number of edges in the network
     * @return number of edges
     */
    public int getNumberOfEdges() {
        return numberOfEdges;
    }
    
    /**
     * Adds an edge to the network
     * @param e the edge to add
     */
    public void addEdge(Edge e) {
        Vertex from = e.getFromVertex();
        Vertex to = e.getTargetVertex();
        
        // Add the edge to the outgoing adjacency list of the source vertex
        adjacenciesList.get(from.getId()).add(e);
        adjacenciesList.get(to.getId()).add(e); // Add the edge to the incoming adjacency list of the target vertex
        
        // Store vertices in the map for getVertexById
        if (!verticesMap.containsKey(from.getId())) {
            verticesMap.put(from.getId(), from);
        }
        if (!verticesMap.containsKey(to.getId())) {
            verticesMap.put(to.getId(), to);
        }
        
        // Increment edge count
        numberOfEdges++;
    }
    
    /**
     * Gets all edges connected to a vertex
     * @param v the vertex
     * @return list of edges
     */
    public List<Edge> getAdjacenciesList(Vertex v) {
        return adjacenciesList.get(v.getId());
    }
    
    /**
     * Gets a vertex by its ID
     * @param id the vertex ID
     * @return the vertex with the specified ID, or null if not found
     */
    public Vertex getVertexById(int id) {
        return verticesMap.get(id);
    }
}
