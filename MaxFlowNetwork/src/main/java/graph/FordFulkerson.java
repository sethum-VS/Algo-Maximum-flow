package graph;

import java.util.LinkedList;
import java.util.Queue;

public class FordFulkerson {
    // Attributes for computing max flow
    private boolean[] marked;
    private Edge[] edgeTo;
    private double valueMaxFlow;

    /**
     * Computes max flow from source s to sink t in the given network
     */
    public FordFulkerson(FlowNetwork network, Vertex s, Vertex t) {
        while (hasAugmentingPath(network, s, t)) {
            // find bottleneck capacity
            double bottle = Double.POSITIVE_INFINITY;
            for (Vertex v = t; v != s ; v  = edgeTo[v.getId()].getOtherVertex(v)) {
                bottle = Math.min(bottle, edgeTo[v.getId()].getResidualCapacity(v));
            }
            // augment flow along path
            for (Vertex v = t; v != s; v = edgeTo[v.getId()].getOtherVertex(v)) {
                edgeTo[v.getId()].addResidualFlowTo(v, bottle);
            }
            valueMaxFlow = valueMaxFlow + bottle;
        }
    }

    /**
     * BFS to find augmenting path in residual network
     */
    private boolean hasAugmentingPath(FlowNetwork network, Vertex s, Vertex t) {
        marked = new boolean[network.getNumberOfVertices()];
        edgeTo = new Edge[network.getNumberOfVertices()];
        Queue<Vertex> queue = new LinkedList<>();

        marked[s.getId()] = true;
        queue.add(s);

        while (!queue.isEmpty() && !marked[t.getId()]) {
            Vertex v = queue.poll();
            for (Edge e : network.getAdjacenciesList(v)) {
                Vertex w = e.getOtherVertex(v);
                if (e.getResidualCapacity(w) > 0 && !marked[w.getId()]) {
                    edgeTo[w.getId()] = e;
                    marked[w.getId()] = true;
                    queue.add(w);
                }
            }
        }
        return marked[t.getId()];
    }

    /**
     * Returns the computed max flow value
     */
    public double getMaxFlow() {
        return valueMaxFlow;
    }

    /**
     * Checks if vertex with given id is on source side of min-cut
     */
    public boolean isInCut(int vertexId) {
        return marked[vertexId];
    }
}
