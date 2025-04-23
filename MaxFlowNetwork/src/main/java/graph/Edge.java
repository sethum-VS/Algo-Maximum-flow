package graph;

/**
 * Models a directed edge in a flow network with capacity and flow.
 */
public class Edge {
    // Attributes
    private Vertex fromVertex;
    private Vertex targetVertex;
    private double capacity;
    private double flow;
    
    /**
     * Constructs an edge with specified vertices and capacity
     * @param from the source vertex
     * @param to the target vertex
     * @param capacity the maximum flow capacity
     */
    public Edge(Vertex from, Vertex to, double capacity) {
        this.fromVertex = from;
        this.targetVertex = to;
        this.capacity = capacity;
        this.flow = 0.0; // Initial flow is zero
    }
    
    /**
     * Copy constructor to create a clone of an existing edge
     * @param edge the edge to copy
     */
    public Edge(Edge edge) {
        this.fromVertex = edge.fromVertex;
        this.targetVertex = edge.targetVertex;
        this.capacity = edge.capacity;
        this.flow = edge.flow;
    }
    
    /**
     * Gets the source vertex of the edge
     * @return the from vertex
     */
    public Vertex getFromVertex() {
        return fromVertex;
    }
    
    /**
     * Gets the target vertex of the edge
     * @return the target vertex
     */
    public Vertex getTargetVertex() {
        return targetVertex;
    }
    
    /**
     * Gets the capacity of the edge
     * @return the capacity
     */
    public double getCapacity() {
        return capacity;
    }
    
    /**
     * Gets the current flow on the edge
     * @return the flow
     */
    public double getFlow() {
        return flow;
    }
    
    /**
     * Sets the flow on the edge
     * @param flow the flow to set
     */
    public void setFlow(double flow) {
        this.flow = flow;
    }
    
    /**
     * Gets the vertex on the other end of the edge
     * @param v a vertex of this edge
     * @return the other vertex
     * @throws IllegalArgumentException if v is not an endpoint of this edge
     */
    public Vertex getOtherVertex(Vertex v) {
        if (v.equals(fromVertex)) {
            return targetVertex;
        } else if (v.equals(targetVertex)) {
            return fromVertex;
        } else {
            throw new IllegalArgumentException("Vertex is not incident to this edge");
        }
    }
    
    /**
     * Gets the residual capacity towards vertex v
     * @param v the vertex to calculate residual capacity towards
     * @return the residual capacity
     * @throws IllegalArgumentException if v is not an endpoint of this edge
     */
    public double getResidualCapacity(Vertex v) {
        if (v.equals(fromVertex)) {
            // Backward edge - can reduce flow
            return flow;
        } else if (v.equals(targetVertex)) {
            // Forward edge - can add up to capacity - flow
            return capacity - flow;
        } else {
            throw new IllegalArgumentException("Vertex is not incident to this edge");
        }
    }
    
    /**
     * Adds residual flow towards vertex v
     * @param v the vertex to push flow towards
     * @param deltaFlow the amount of flow to add
     * @throws IllegalArgumentException if v is not an endpoint of this edge
     */
    public void addResidualFlowTo(Vertex v, double deltaFlow) {
        if (v.equals(fromVertex)) {
            // Backward edge - decrease flow
            flow -= deltaFlow;
        } else if (v.equals(targetVertex)) {
            // Forward edge - increase flow
            flow += deltaFlow;
        } else {
            throw new IllegalArgumentException("Vertex is not incident to this edge");
        }
    }
    
    /**
     * String representation of the edge
     * @return string in format "from -> to, flow: x, capacity: y"
     */
    @Override
    public String toString() {
        return fromVertex.getId() + " -> " + targetVertex.getId() + 
               ", flow: " + flow + ", capacity: " + capacity;
    }
}
