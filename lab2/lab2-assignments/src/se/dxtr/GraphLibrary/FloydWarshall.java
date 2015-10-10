package se.dxtr.graphlibrary;

import java.util.List;

/**
 * Utility class with methods for finding shortest paths between all pairs of vertices in a graph.
 * <p>
 * Authors:
 * Dexter Gramfors, Ludvig Jansson
 */
public class FloydWarshall {

    public static final int INFINITY = Integer.MAX_VALUE;
    public static final int NEGATIVE_INFINITY = Integer.MIN_VALUE;

    /**
     * Use the Floyd-Warshall algorithm to find the shortest paths between all pairs of vertices in a graph.
     *
     * @param graph the graph in which to find the shortest paths
     * @return a matrix representing the shortest distances between all pairs of distances in the graph
     */
    public static int[][] shortestPath (Graph<Weight> graph) {
        List<Vertex<Weight>> vertices = graph.getVertices ();
        // Initialize distance between all pairs as unknown (except to the vertex itself)
        int[][] distance = new int[vertices.size ()][vertices.size ()];
        for (int i = 0; i < vertices.size (); i++) {
            for (int j = 0; j < vertices.size (); j++) {
                distance[i][j] = i == j ? 0 : INFINITY;
            }
        }

        // Set distances where there is an edge between to vertices to the weight of the edge
        for (Edge<Weight> edge : graph.getEdges ()) {
            int fromId = edge.getFrom ().getId ();
            int toId = edge.getTo ().getId ();
            distance[fromId][toId] = Math.min (edge.getData ().weight, distance[fromId][toId]);
        }

        // Constantly perform updates such that the distance between i and j using vertices 0..k is distance[i][j]
        for (int k = 0; k < vertices.size (); k++) {
            for (int i = 0; i < vertices.size (); i++) {
                for (int j = 0; j < vertices.size (); j++) {
                    if (distance[i][k] == INFINITY || distance[k][j] == INFINITY) {
                        continue;
                    } else {
                        if (distance[i][k] == NEGATIVE_INFINITY || distance[k][j] == NEGATIVE_INFINITY) {
                            distance[i][j] = NEGATIVE_INFINITY;
                        } else if (distance[i][k] + distance[k][j] < distance[i][j]) {
                            distance[i][j] = distance[i][k] + distance[k][j];
                        }
                    }
                }
                if (distance[i][i] < 0)
                    propagateNegativeCycle (distance, graph, i);
            }
        }

        return distance;
    }

    /**
     * Propagate arbirarily short distances from a vertex, as all nodes reachable from a node reachable from the
     * vertex which has a negative cycle to itself have arbitrarily short paths from the vertex.
     */
    private static void propagateNegativeCycle (int[][] dist, Graph<Weight> graph, int index) {
        dist[index][index] = NEGATIVE_INFINITY;
        for (Edge<Weight> edge : graph.getVertices ().get (index).getEdges ())
            propagateNegativeCycle (dist, graph, edge.getTo ().getId (), index);
    }

    /**
     * Helper for recursively propagating a negative cycle.
     */
    private static void propagateNegativeCycle (int[][] dist, Graph<Weight> graph, int index, int prevIndex) {
        if (dist[prevIndex][index] > NEGATIVE_INFINITY) {
            dist[prevIndex][index] = NEGATIVE_INFINITY;
            for (Edge<Weight> edge : graph.getVertices ().get (index).getEdges ())
                propagateNegativeCycle (dist, graph, edge.getTo ().getId (), index);
        }
    }
}
