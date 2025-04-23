package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import graph.FlowNetwork;
import graph.Vertex;
import graph.Edge;

public class NetworkParser {
    /**
     * Parses a flow network from a text file.
     * @param filePath path to the input file
     * @return constructed FlowNetwork
     * @throws IOException if file reading fails
     */
    public static FlowNetwork parseFromFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String firstLine = reader.readLine();
        int n = Integer.parseInt(firstLine.trim());
        // Create vertices
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i, "v" + i);
        }
        // Initialize network
        FlowNetwork network = new FlowNetwork(n);
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split("\\s+");
            int a = Integer.parseInt(parts[0]);
            int b = Integer.parseInt(parts[1]);
            double c = Double.parseDouble(parts[2]);
            Edge edge = new Edge(vertices[a], vertices[b], c);
            network.addEdge(edge);
        }
        reader.close();
        return network;
    }
}
