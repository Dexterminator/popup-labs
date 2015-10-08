package se.dxtr.graphlibrary;

import java.util.*;

/**
 * Created by dexter on 06/10/15.
 */
public class Dijkstra {

    public static DistanceResult shortestPath (Graph<Weight> graph, Vertex<Weight> start) {
        long[] distance = new long[graph.getVertices ().size ()];
        Vertex[] parent = new Vertex[graph.getVertices ().size ()];
        distance[start.getId ()] = 0;

        TreeSet<Vertex<Weight>> queue = new TreeSet<> ((vertex1, vertex2) -> {
            if (distance[vertex1.getId ()] == distance[vertex2.getId ()]) {
                return vertex1.getId () - vertex2.getId ();
            }

            if (distance[vertex1.getId ()] - distance[vertex2.getId ()] < 0)
                return -1;
            return 1;
        });

        for (Vertex<Weight> vertex : graph.getVertices ()) {
            if (vertex.getId () != start.getId ()) {
                distance[vertex.getId ()] = Integer.MAX_VALUE;
                parent[vertex.getId ()] = null;
            }
            queue.add (vertex);
        }

        while (!queue.isEmpty ()) {
            Vertex<Weight> current = queue.pollFirst ();
            for (Edge<Weight> edge : current.getEdges ()) {
                Vertex<Weight> to = edge.getTo ();
                long alternativeDistance = distance[current.getId ()] + edge.getData ().weight;
                if (alternativeDistance < distance[to.getId ()]) {
                    distance[to.getId ()] = alternativeDistance;
                    parent[to.getId ()] = current;
                    queue.remove (to);
                    queue.add (to);
                }
            }
        }

        return new DistanceResult (distance, parent);
    }

    public static DistanceResult shortestTimeTablePath (Graph<TimeTable> graph, Vertex<TimeTable> start) {
        long[] distance = new long[graph.getVertices ().size ()];
        Vertex[] parent = new Vertex[graph.getVertices ().size ()];
        distance[start.getId ()] = 0;

        TreeSet<Vertex<TimeTable>> queue = new TreeSet<> ((vertex1, vertex2) -> {
            if (distance[vertex1.getId ()] == distance[vertex2.getId ()]) {
                return vertex1.getId () - vertex2.getId ();
            }

            if (distance[vertex1.getId ()] - distance[vertex2.getId ()] < 0)
                return -1;
            return 1;
        });

        for (Vertex<TimeTable> vertex : graph.getVertices ()) {
            if (vertex.getId () != start.getId ()) {
                distance[vertex.getId ()] = Integer.MAX_VALUE;
                parent[vertex.getId ()] = null;
            }
            queue.add (vertex);
        }

        while (!queue.isEmpty ()) {
            Vertex<TimeTable> current = queue.pollFirst ();
            for (Edge<TimeTable> edge : current.getEdges ()) {
                Vertex<TimeTable> to = edge.getTo ();
                TimeTable timeTable = edge.getData ();
                if (timeTable.interval != 0 || (timeTable.interval == 0 && timeTable.t0 >= distance[current.getId ()])) {
                    long alternativeDistance;
                    if (timeTable.t0 >= distance[current.getId ()]) {
                        long wait = timeTable.t0 - distance[current.getId ()];
                        alternativeDistance = distance[current.getId ()] + timeTable.traversalTime + wait;
                    } else {
                        long mult = 1 + (distance[current.getId ()] - timeTable.t0 - 1) / timeTable.interval;
                        alternativeDistance = timeTable.t0 + mult * timeTable.interval + timeTable.traversalTime;
                    }
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
}
