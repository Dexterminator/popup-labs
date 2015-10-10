package se.dxtr.graphlibrary;

import java.util.List;

/**
 * Created by dexter on 07/10/15.
 */
public class FloydWarshall {
    public static int[][] shortestPath (Graph<Weight> graph) {
        List<Vertex<Weight>> vertices = graph.getVertices ();
        List<Edge<Weight>> edges = graph.getEdges ();
        int[][] dist = new int[vertices.size ()][vertices.size ()];
        for (int i = 0; i < vertices.size (); i++) {
            for (int j = 0; j < vertices.size (); j++) {
                if (i == j)
                    dist[i][j] = 0;
                else
                    dist[i][j] = Integer.MAX_VALUE;
            }
        }

        for (Edge<Weight> edge : edges) {
            int fromId = edge.getFrom ().getId ();
            int toId = edge.getTo ().getId ();
            dist[fromId][toId] = Math.min (edge.getData ().weight, dist[fromId][toId]);
        }

        for (int k = 0; k < vertices.size (); k++) {
            for (int i = 0; i < vertices.size (); i++) {
                for (int j = 0; j < vertices.size (); j++) {
                    if ((dist[i][k] == Integer.MAX_VALUE || dist[k][j] == Integer.MAX_VALUE)) {
                        continue; //  No edge between these two
                    } else {
                        if (dist[i][k] == Integer.MIN_VALUE || dist[k][j] == Integer.MIN_VALUE) {
                            dist[i][j] = Integer.MIN_VALUE; // Infinitely small edge
                        } else if (dist[i][k] + dist[k][j] < dist[i][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j]; // New, better path
                        }
                    }
                }
                if (dist[i][i] < 0)
                    propagateNegativeCycle (dist, graph, i);
            }
        }

        return dist;
    }

    private static void propagateNegativeCycle (int[][] dist, Graph<Weight> graph, int index) {
        dist[index][index] = Integer.MIN_VALUE;
        for (Edge<Weight> edge : graph.getVertices ().get (index).getEdges ()) {
            propagateNegativeCycle (dist, graph, edge.getTo ().getId (), index);
        }
    }

    private static void propagateNegativeCycle (int[][] dist, Graph<Weight> graph, int index, int prevIndex) {
        if (dist[prevIndex][index] > Integer.MIN_VALUE) {
            dist[prevIndex][index] = Integer.MIN_VALUE;
            for (Edge<Weight> edge : graph.getVertices ().get (index).getEdges ()) {
                propagateNegativeCycle (dist, graph, edge.getTo ().getId (), index);
            }
        }
    }
}
