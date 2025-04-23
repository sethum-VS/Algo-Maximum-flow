package graph;

public class Vertex {
    // Attributes
    private int id;
    private String name;
    private boolean visited;
    
    /**
     * Constructor for Vertex
     * @param id unique identifier for the vertex
     * @param name human-readable name of the vertex
     */
    public Vertex(int id, String name) {
        this.id = id;
        this.name = name;
        this.visited = false;
    }
    
    /**
     * Resets the visited flag to false
     */
    public void resetVisited() {
        this.visited = false;
    }
    
    /**
     * Gets the vertex ID
     * @return the vertex ID
     */
    public int getId() {
        return id;
    }
    
    /**
     * Sets the vertex ID
     * @param id the ID to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Gets the vertex name
     * @return the vertex name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the vertex name
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Checks if the vertex has been visited
     * @return true if visited, false otherwise
     */
    public boolean isVisited() {
        return visited;
    }
    
    /**
     * Sets the visited status
     * @param visited the visited status to set
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    
    /**
     * String representation of the vertex
     * @return string in format "id name"
     */
    @Override
    public String toString() {
        return id + " " + name;
    }
}
