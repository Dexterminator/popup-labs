package se.dxtr.graphlibrary;

import java.util.List;

/**
 * Created by Ludde on 15-10-07.
 */
public class BellmanFord {

    public static DistanceResult shortestPath(Graph<Weight> graph, Vertex<Weight> start){
        long[] distance = new long[graph.getVertices ().size ()];
        Vertex[] parent = new Vertex[graph.getVertices ().size ()];

        for (Vertex<Weight> vertex : graph.getVertices ()) {
            if (vertex.getId () != start.getId ()) {
                distance[vertex.getId ()] = Integer.MAX_VALUE;
            }
        }
        distance[start.getId ()] = 0;

        List<Vertex<Weight>> vertexList = graph.getVertices();
        List<Edge<Weight>> edgeList = graph.getEdges();
        for (int i = 0; i < vertexList.size()-1; i++) {
            for (Edge<Weight> edge : edgeList) {
                if(distance[edge.getFrom().getId()] != Integer.MAX_VALUE && (edge.getData().weight + distance[edge.getFrom().getId()] < distance[edge.getTo().getId()])){
                    distance[edge.getTo().getId()] = distance[edge.getFrom().getId()] + edge.getData().weight;
                    parent[edge.getTo().getId()] = edge.getFrom();
                }
            }
        }

        for (Edge<Weight> edge : edgeList) {
            if(distance[edge.getFrom().getId()] != Integer.MAX_VALUE && distance[edge.getFrom().getId()] + edge.getData().weight < distance[edge.getTo().getId()]) {
                propagateNegativeCycle(graph, distance, edge.getTo().getId());
            }
        }
        return new DistanceResult(distance, parent);
    }

    public static void propagateNegativeCycle(Graph<Weight> graph, long[] distance, int vertexId){
        if(distance[vertexId] > Integer.MIN_VALUE){
            distance[vertexId] = Integer.MIN_VALUE;
            for (Edge<Weight> edge : graph.getVertices().get(vertexId).getEdges()) {
                propagateNegativeCycle(graph, distance, edge.getTo().getId());
            }
        }

    }
}
