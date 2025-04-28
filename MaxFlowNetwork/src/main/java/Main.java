import java.util.Scanner;
import java.io.IOException;
import graph.FlowNetwork;
import graph.Vertex;
import graph.Edge;
import graph.FordFulkerson;
import parser.NetworkParser;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Maximum Flow CLI ===");
            System.out.println("1. Enter custom graph manually");
            System.out.println("2. Load and run benchmark graph from file");
            System.out.println("3. Quit");
            System.out.print("Select an option (1-3): ");
            String input = scanner.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
                continue;
            }
            switch (choice) {
                case 1:
                    buildCustomGraph(scanner);
                    break;
                case 2:
                    loadBenchmarkGraph(scanner);
                    break;
                case 3:
                    System.out.println("Exiting application. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please select 1, 2, or 3.");
            }
        }
    }

    /**
     * Builds a custom graph from console input and computes max flow.
     */
    private static void buildCustomGraph(Scanner scanner) {
        try {
            System.out.print("Enter number of vertices: ");
            int n = Integer.parseInt(scanner.nextLine().trim());
            Vertex[] vertices = new Vertex[n];
            for (int i = 0; i < n; i++) {
                vertices[i] = new Vertex(i, "v" + i);
            }
            FlowNetwork network = new FlowNetwork(n);
            System.out.println("Enter edges in format: from to capacity (enter -1 to finish)");
            while (true) {
                System.out.print("Edge: ");
                String line = scanner.nextLine().trim();
                if (line.equals("-1")) {
                    break;
                }
                String[] parts = line.split("\\s+");
                if (parts.length != 3) {
                    System.out.println("Invalid format. Expected three values: from to capacity.");
                    continue;
                }
                int from = Integer.parseInt(parts[0]);
                int to = Integer.parseInt(parts[1]);
                double cap = Double.parseDouble(parts[2]);
                if (from < 0 || from >= n || to < 0 || to >= n) {
                    System.out.println("Vertex IDs must be between 0 and " + (n-1));
                    continue;
                }
                network.addEdge(new Edge(vertices[from], vertices[to], cap));
            }
            computeAndDisplayMaxFlow(network);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Returning to main menu.");
        }
    }

    /**
     * Loads a graph from a file using NetworkParser and computes max flow.
     */
    private static void loadBenchmarkGraph(Scanner scanner) {
        System.out.print("Enter file path or press enter to run default path (default 'benchmarks/bridge_1.txt): ");
        String path = scanner.nextLine().trim();
        if (path.isEmpty()) {
            path = "E:\\1 work\\2nd yr\\ALGO\\CW\\bench\\benchmarks\\bridge_1.txt";
        }
        try {
            FlowNetwork network = NetworkParser.parseFromFile(path);
            computeAndDisplayMaxFlow(network);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Runs Ford-Fulkerson on the given network and displays results.
     */
    private static void computeAndDisplayMaxFlow(FlowNetwork network) {
        int n = network.getNumberOfVertices();
        Vertex source = network.getVertexById(0);
        Vertex sink = network.getVertexById(n - 1);
        FordFulkerson ff = new FordFulkerson(network, source, sink);
        System.out.println("\n--- Results ---");
        System.out.println("Maximum flow from vertex 0 to " + (n-1) + ": " + ff.getMaxFlow());
        System.out.print("Min-cut source side vertices: ");
        for (int i = 0; i < n; i++) {
            if (ff.isInCut(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
}
