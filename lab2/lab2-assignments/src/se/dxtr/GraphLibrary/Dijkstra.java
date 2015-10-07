package se.dxtr.graphlibrary;

import java.util.*;

/**
 * Created by dexter on 06/10/15.
 */
public class Dijkstra {

    public static DistanceResult shortestPath (Graph<Void, Weight> graph, Vertex<Void, Weight> start) {
        long[] distance = new long[graph.getVertices ().size ()];
        Vertex[] parent = new Vertex[graph.getVertices ().size ()];
        distance[start.getId ()] = 0;

        TreeSet<Vertex<Void, Weight>> queue = new TreeSet<> ((vertex1, vertex2) -> {
            if (distance[vertex1.getId ()] == distance[vertex2.getId ()]) {
                return vertex1.getId () - vertex2.getId ();
            }

            if (distance[vertex1.getId ()] - distance[vertex2.getId ()] < 0)
                return -1;
            return 1;
        });

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
                Vertex<Void, Weight> to = edge.getTo ();
                long alternativeDistance = distance[current.getId ()] + edge.getData ().weight;
                if (alternativeDistance < distance[to.getId ()]) {
                    distance[to.getId ()] = alternativeDistance;
                    parent[to.getId ()] = current;
                    queue.remove (to);
                    queue.add (to);
                }
            }
        }

        return new DistanceResult(distance, parent);
    }

    public static DistanceResult shortestTimeTablePath (Graph<Void, TimeTable> graph, Vertex<Void, TimeTable> start) {
        long[] distance = new long[graph.getVertices ().size ()];
        Vertex[] parent = new Vertex[graph.getVertices ().size ()];
        distance[start.getId ()] = 0;
        long time = 0;

        TreeSet<Vertex<Void, TimeTable>> queue = new TreeSet<> ((vertex1, vertex2) -> {
            if (distance[vertex1.getId ()] == distance[vertex2.getId ()]) {
                return vertex1.getId () - vertex2.getId ();
            }

            if (distance[vertex1.getId ()] - distance[vertex2.getId ()] < 0)
                return -1;
            return 1;
        });

        for (Vertex<Void, TimeTable> vertex : graph.getVertices ()) {
            if (vertex.getId () != start.getId ()) {
                distance[vertex.getId ()] = Integer.MAX_VALUE;
                parent[vertex.getId ()] = null;
            }
            queue.add (vertex);
        }

        while (!queue.isEmpty ()) {
            Vertex<Void, TimeTable> current = queue.pollFirst ();
            for (Edge<Void, TimeTable> edge : current.getEdges ()) {
                Vertex<Void, TimeTable> to = edge.getTo ();
                //TODO
                TimeTable timeTable = edge.getData ();
                long timeDistance;
                if (timeTable.interval == 0) {
                    if (time == timeTable.t0)
                        timeDistance = timeTable.traversalTime;
                    else
                        continue;
                } else {
                    long temp = timeTable.t0;
                    while (temp < time) {
                        temp += timeTable.interval;
                    }
                    timeDistance = (long) timeTable.traversalTime + (temp - time);
                }

                long alternativeDistance = distance[current.getId ()] + timeDistance;
                if (alternativeDistance < distance[to.getId ()]) {
                    distance[to.getId ()] = alternativeDistance;
                    parent[to.getId ()] = current;
                    queue.remove (to);
                    queue.add (to);
                }
            }
        }

        return new DistanceResult(distance, parent);
    }

//    public static class DijkstraResult {
//        public final Vertex[] parent;
//        public final long[] distance;
//
//        public DijkstraResult (long[] distance, Vertex[] parent) {
//            this.parent = parent;
//            this.distance = distance;
//        }
//    }
}
