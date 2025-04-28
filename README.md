# Max-Flow Calculator (Edmonds-Karp)

## Description

A command-line Java application that computes maximum flow and minimum cut in flow networks. This tool implements the Ford-Fulkerson algorithm with the Edmonds-Karp strategy to find the maximum possible flow from a source to a sink in a directed graph with capacity constraints on edges. The application allows users to either manually input graph data or load graphs from benchmark files.

## Algorithm

The Edmonds-Karp algorithm is an implementation of the Ford-Fulkerson method that specifically uses Breadth-First Search (BFS) to find augmenting paths. By always selecting the shortest augmenting path, the algorithm ensures a guaranteed worst-case running time of O(V · E²), where V is the number of vertices and E is the number of edges in the graph. The minimum cut (a partition of vertices that determines the network's bottleneck) is also computed during the algorithm's execution.

## Tech Stack

| Layer      | Tool                            |
|------------|--------------------------------|
| Language   | Java 17 (or compatible JDK)    |
| Build Tool | Maven (pom.xml based)          |
| IDE        | VS Code / IntelliJ IDEA        |
| Logging    | Custom GraphUtils for CLI tracing |

## Project Structure

```
MaxFlowNetwork/
├── pom.xml                  # Maven project configuration
├── src/
│   └── main/
│       └── java/
│           ├── Main.java    # CLI application entry point
│           ├── graph/
│           │   ├── Edge.java          # Edge with flow/capacity properties
│           │   ├── FlowNetwork.java   # Graph representation of flow network
│           │   ├── FordFulkerson.java # Edmonds-Karp algorithm implementation
│           │   └── Vertex.java        # Vertex representation
│           ├── parser/
│           │   └── NetworkParser.java # File parser for loading benchmark graphs
│           └── util/
│               └── GraphUtils.java    # Utilities for logging algorithm steps
```

## Features

- Interactive command-line interface
- Manual graph construction
- Loading benchmark graphs from files
- Computing maximum flow and displaying results
- Finding minimum cut in the network
- Detailed logging of augmenting paths

## Usage

1. **Manual Graph Input**:
   - Select option 1 from the main menu
   - Enter number of vertices
   - Add edges with format: `source destination capacity`
   - Enter `-1` when finished adding edges

2. **Load Graph from File**:
   - Select option 2 from the main menu
   - Enter the path to the benchmark file or press Enter to use the default

The file format should be:
```
n           # number of vertices
s t c       # edges: source target capacity
...
```

## Example Output

```
=== Maximum Flow CLI ===
1. Enter custom graph manually
2. Load and run benchmark graph from file
3. Quit
Select an option (1-3): 2

--- Results ---
Maximum flow from vertex 0 to 5: 23.0
Min-cut source side vertices: 0 1 2 
```

