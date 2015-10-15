package se.dxtr.graphlibrary;

import java.util.*;

/**
 * Utility class with methods for finding shortest paths from a single source.
 * <p>
 * Authors:
 * Dexter Gramfors, Ludvig Jansson
 */
public class Dijkstra {

    public static final int INFINITY = Integer.MAX_VALUE;

    /**
     * Uses Dijkstra's algorithm to find the shortest path from a vertex to all other vertices in a graph.
     *
     * @param graph  the graph in which to find the shortest path
     * @param source the vertex from which to find shortest paths
     * @return a DistanceResult containing an array of the lengths of the shortest paths, and a parent array specifying the paths.
     * A distance value of Integer.MAX_VALUE indicates that the vertex is not reachable form the source.
     */
    public static DistanceResult shortestPath (Graph<Weight> graph, Vertex<Weight> source) {
        long[] distance = new long[graph.getVertices ().size ()];
        Vertex[] parent = new Vertex[graph.getVertices ().size ()];
        distance[source.getId ()] = 0;
        NavigableSet<Vertex<Weight>> queue = getVerticesSortedByDistanceQueue (distance);

        // Initialize all distances as unknown
        for (Vertex<Weight> vertex : graph.getVertices ()) {
            if (vertex.getId () != source.getId ())
                distance[vertex.getId ()] = INFINITY;
            queue.add (vertex);
        }

        // Constantly select the cheapest vertex, and update the shortest path array if the path through
        // this vertex to a node is cheaper than the best known path
        while (!queue.isEmpty ()) {
            Vertex<Weight> current = queue.pollFirst ();
            for (Edge<Weight> edge : current.getEdges ()) {
                Vertex<Weight> to = edge.getTo ();
                long alternativeDistance = distance[current.getId ()] + edge.getData ().weight;
                if (alternativeDistance < distance[to.getId ()]) {
                    distance[to.getId ()] = alternativeDistance;
                    parent[to.getId ()] = current;
                    updatePriority (queue, to);
                }
            }
        }

        return new DistanceResult (distance, parent);
    }

    /**
     * Updates the priority of a vertex in the queue.
     */
    private static void updatePriority (NavigableSet<Vertex<Weight>> queue, Vertex<Weight> vertex) {
        queue.remove (vertex);
        queue.add (vertex);
    }

    /**
     * Uses Dijkstra's algorithm to find the shortest path from a vertex to all other vertices in a graph where
     * an edge can only be used at certain points in time.
     *
     * @param graph  the graph in which to find the shortest path
     * @param source the vertex from which to find shortest paths
     * @return a DistanceResult containing an array of the lengths of the shortest paths, and a parent array specifying the paths
     */
    public static DistanceResult shortestTimeTablePath (Graph<TimeTable> graph, Vertex<TimeTable> source) {
        long[] distance = new long[graph.getVertices ().size ()];
        Vertex[] parent = new Vertex[graph.getVertices ().size ()];
        distance[source.getId ()] = 0;
        NavigableSet<Vertex<TimeTable>> queue = getVerticesSortedByDistanceQueue (distance);

        for (Vertex<TimeTable> vertex : graph.getVertices ()) {
            if (vertex.getId () != source.getId ()) {
                distance[vertex.getId ()] = INFINITY;
                parent[vertex.getId ()] = null;
            }
            queue.add (vertex);
        }

        // Constantly select the cheapest vertex considering time table constraints, and update the shortest path array
        // if the path through this vertex to a node is cheaper than the best known path
        while (!queue.isEmpty ()) {
            Vertex<TimeTable> current = queue.pollFirst ();
            for (Edge<TimeTable> edge : current.getEdges ()) {
                Vertex<TimeTable> to = edge.getTo ();
                TimeTable timeTable = edge.getData ();
                int interval = timeTable.interval;
                int t0 = timeTable.t0;
                int traversalTime = timeTable.traversalTime;
                if (traversalPossible (interval, t0, distance, current)) {
                    long alternativeDistance =
                            calculateAlternativeDistance (distance[current.getId ()], interval, t0, traversalTime);
                    if (alternativeDistance < distance[to.getId ()]) {
                        distance[to.getId ()] = alternativeDistance;
                        parent[to.getId ()] = current;
                        queue.remove (to);
                        queue.add (to);
                    }
                }
            }
        }

        return new DistanceResult (distance, parent);
    }

    /**
     * Check if it is possible to traverse an edge with time table constraints, either immediately or by waiting.
     */
    private static boolean traversalPossible (int interval, int t0, long[] distance, Vertex<TimeTable> vertex) {
        return interval != 0 || t0 >= distance[vertex.getId ()];
    }

    /**
     * Calculate a new distance by waiting until it is possible to traverse and adding the wait to the cost
     * of traversing the edge.
     */
    private static long calculateAlternativeDistance (long time, int interval, int t0, int traversalTime) {
        if (t0 >= time) {
            long wait = t0 - time;
            return time + traversalTime + wait;
        }
        long mult = 1 + (time - t0 - 1) / interval;
        return t0 + mult * interval + traversalTime;
    }

    /**
     * Get a queue with vertices sorted by their distance from a source node.
     * Vertices are sorted by ID if their distance is equal.
     */
    private static <V> NavigableSet<Vertex<V>> getVerticesSortedByDistanceQueue (long[] distance) {
        return new TreeSet<> ((vertex1, vertex2) -> {
            if (distance[vertex1.getId ()] == distance[vertex2.getId ()])
                return vertex1.getId () - vertex2.getId ();
            if (distance[vertex1.getId ()] - distance[vertex2.getId ()] < 0)
                return -1;
            return 1;
        });
    }
}
