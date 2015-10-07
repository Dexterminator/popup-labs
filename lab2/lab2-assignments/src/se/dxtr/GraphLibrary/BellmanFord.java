package se.dxtr.graphlibrary;

import java.util.List;

/**
 * Created by Ludde on 15-10-07.
 */
public class BellmanFord {

    public static DistanceResult shortestPath(Graph<Void, Weight> graph, Vertex<Void, Weight> start){
        long[] distance = new long[graph.getVertices ().size ()];
        Vertex[] parent = new Vertex[graph.getVertices ().size ()];
        distance[start.getId ()] = 0;

        for (Vertex<Void, Weight> vertex : graph.getVertices ()) {
            if (vertex.getId () != start.getId ()) {
                distance[vertex.getId ()] = Integer.MAX_VALUE;
            }
        }
        
        List<Vertex<Void, Weight>> vertexList = graph.getVertices();
        List<Edge<Void, Weight>> edgeList = graph.getEdges();
        for (int i = 0; i < vertexList.size(); i++) {
            for (Edge<Void, Weight> edge : edgeList) {
                if(distance[edge.getFrom().getId()] + edge.getData().weight < distance[edge.getTo().getId()]){
                    distance[edge.getTo().getId()] = distance[edge.getFrom().getId()] + edge.getData().weight;
                    parent[edge.getTo().getId()] = edge.getFrom();
                }
            }
        }

        for (Edge<Void, Weight> edge : edgeList) {
            if(distance[edge.getFrom().getId()] + edge.getData().weight < distance[edge.getTo().getId()]) {
                distance[edge.getTo().getId()] = Integer.MIN_VALUE;
            }
        }
        return new DistanceResult(distance, parent);
    }
}
