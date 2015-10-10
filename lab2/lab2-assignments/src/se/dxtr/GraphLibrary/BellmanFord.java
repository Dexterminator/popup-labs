package se.dxtr.graphlibrary;

/**
 * Utility class with methods for finding shortest paths from a single source where edge weights may be negative.
 * <p>
 * Authors:
 * Dexter Gramfors, Ludvig Jansson
 */
public class BellmanFord {

    public static final int INFINITY = Integer.MAX_VALUE;
    public static final int NEGATIVE_INFINITY = Integer.MIN_VALUE;

    /**
     * Uses the Bellman-Ford algorithm to find the shortest path from a vertex to all other vertices in a graph where
     * edge weights may be negative.
     *
     * @param graph  the graph in which to find the shortest path
     * @param source the vertex from which to find shortest paths
     * @return a DistanceResult containing an array of the lengths of the shortest paths, and a parent array specifying the paths.
     * A distance value of Integer.MAX_VALUE indicates that a vertex is not reachable from the souce, and a distance
     * value of INTEGER.MIN_VALUE indicates that the path to a vertex from the source is arbitrarily short.
     */
    public static DistanceResult shortestPath (Graph<Weight> graph, Vertex<Weight> source) {
        long[] distance = new long[graph.getVertices ().size ()];
        Vertex[] parent = new Vertex[graph.getVertices ().size ()];

        for (Vertex<Weight> vertex : graph.getVertices ())
            distance[vertex.getId ()] = INFINITY;
        distance[source.getId ()] = 0;

        // Constantly update the shortest path array if the path through the current to another node shortens the
        // path from the source to this node
        for (int i = 0; i < graph.getVertices ().size () - 1; i++) {
            for (Edge<Weight> edge : graph.getEdges ()) {
                int fromId = edge.getFrom ().getId ();
                int toId = edge.getTo ().getId ();
                if (distance[fromId] != INFINITY &&
                        edge.getData ().weight + distance[fromId] < distance[toId]) {
                    distance[toId] = distance[fromId] + edge.getData ().weight;
                    parent[toId] = edge.getFrom ();
                }
            }
        }

        // Check for negative cycles that result in arbitrarily short paths
        for (Edge<Weight> edge : graph.getEdges ()) {
            int fromId = edge.getFrom ().getId ();
            int toId = edge.getTo ().getId ();
            if (distance[fromId] != INFINITY && distance[fromId] + edge.getData ().weight < distance[toId])
                propagateNegativeCycle (graph, distance, toId);
        }

        return new DistanceResult (distance, parent);
    }

    /**
     * Propagate arbirarily short distances from the source, as all nodes reachable from a node reachable from the
     * source which has a negative cycle to itself have arbitrarily short paths from the source.
     */
    public static void propagateNegativeCycle (Graph<Weight> graph, long[] distance, int vertexId) {
        if (distance[vertexId] > NEGATIVE_INFINITY) {
            distance[vertexId] = NEGATIVE_INFINITY;
            for (Edge<Weight> edge : graph.getVertices ().get (vertexId).getEdges ()) {
                propagateNegativeCycle (graph, distance, edge.getTo ().getId ());
            }
        }

    }
}
