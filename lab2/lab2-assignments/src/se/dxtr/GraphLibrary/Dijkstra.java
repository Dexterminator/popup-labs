package se.dxtr.graphlibrary;

import java.util.TreeSet;

/**
 * Created by dexter on 06/10/15.
 */
public class Dijkstra {

    public static DijkstraResult shortestPath (Graph<Void, Weight> graph, Vertex<Void, Weight> start) {
        int[] distance = new int[graph.getVertices ().size ()];
        Vertex[] parent = new Vertex[graph.getVertices ().size ()];
        distance[start.getId ()] = 0;

        TreeSet<Vertex<Void, Weight>> queue =
                new TreeSet<> ((vertex1, vertex2) -> distance[vertex1.getId ()] - distance[vertex2.getId ()]);
        for (Vertex<Void, Weight> vertex : graph.getVertices ()) {
            if (vertex.getId () != start.getId ()) {
                distance[vertex.getId ()] = Integer.MAX_VALUE;
                parent[vertex.getId ()] = null;
            }
            queue.add (vertex);
        }

        while (!queue.isEmpty ()) {
            Vertex<Void, Weight> current = queue.pollFirst ();
            for (Edge<Void, Weight> edge : current.getEdges ()) {
                Vertex to = edge.getTo ();
                int altternativeDistance = distance[current.getId ()] + edge.getData ().weight;
                if (altternativeDistance < distance[to.getId ()]) {
                    distance[to.getId ()] = altternativeDistance;
                    parent[to.getId ()] = current;
                    queue.remove (to);
                    queue.add (to);
                }
            }
        }

        return new DijkstraResult (distance, parent);
    }

    public static class DijkstraResult {
        public final Vertex[] parent;
        public final int[] distance;

        public DijkstraResult (int[] distance, Vertex[] parent) {
            this.parent = parent;
            this.distance = distance;
        }
    }
}
