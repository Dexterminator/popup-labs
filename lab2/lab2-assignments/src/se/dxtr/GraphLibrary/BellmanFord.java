package se.dxtr.graphlibrary;

import java.util.List;

/**
 * Created by Ludde on 15-10-07.
 */
public class BellmanFord {

    public static final int INFINITY = Integer.MAX_VALUE;
    public static final int NEGATIVE_INFINITY = Integer.MIN_VALUE;

    public static DistanceResult shortestPath (Graph<Weight> graph, Vertex<Weight> start) {
        long[] distance = new long[graph.getVertices ().size ()];
        Vertex[] parent = new Vertex[graph.getVertices ().size ()];

        for (Vertex<Weight> vertex : graph.getVertices ())
            distance[vertex.getId ()] = INFINITY;
        distance[start.getId ()] = 0;

        List<Vertex<Weight>> vertexList = graph.getVertices ();
        List<Edge<Weight>> edgeList = graph.getEdges ();
        for (int i = 0; i < vertexList.size () - 1; i++) {
            for (Edge<Weight> edge : edgeList) {
                int fromId = edge.getFrom ().getId ();
                int toId = edge.getTo ().getId ();
                if (distance[fromId] != INFINITY &&
                        (edge.getData ().weight + distance[fromId] < distance[toId])) {
                    distance[toId] = distance[fromId] + edge.getData ().weight;
                    parent[toId] = edge.getFrom ();
                }
            }
        }

        for (Edge<Weight> edge : edgeList) {
            int fromId = edge.getFrom ().getId ();
            int toId = edge.getTo ().getId ();
            if (distance[fromId] != INFINITY && distance[fromId] + edge.getData ().weight < distance[toId])
                propagateNegativeCycle (graph, distance, toId);
        }

        return new DistanceResult (distance, parent);
    }

    public static void propagateNegativeCycle (Graph<Weight> graph, long[] distance, int vertexId) {
        if (distance[vertexId] > NEGATIVE_INFINITY) {
            distance[vertexId] = NEGATIVE_INFINITY;
            for (Edge<Weight> edge : graph.getVertices ().get (vertexId).getEdges ()) {
                propagateNegativeCycle (graph, distance, edge.getTo ().getId ());
            }
        }

    }
}
