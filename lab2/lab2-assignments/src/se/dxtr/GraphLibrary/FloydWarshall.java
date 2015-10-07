package se.dxtr.graphlibrary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dexter on 07/10/15.
 */
public class FloydWarshall {
    public static int[][] shortestPath(Graph<Void, Weight> graph) {
        List<Vertex<Void, Weight>> vertices = graph.getVertices();
        List<Edge<Void, Weight>> edges = graph.getEdges();
        int[][] dist = new int[vertices.size()][vertices.size()];
        for (int i = 0; i <vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                if(i == j)
                    dist[i][j] = 0;
                else
                    dist[i][j] = Integer.MAX_VALUE;
            }
        }

        for (Edge<Void, Weight> edge : edges) {
            dist[edge.getFrom().getId()][edge.getTo().getId()] = Math.min(edge.getData().weight,dist[edge.getFrom().getId()][edge.getTo().getId()] );
        }
            for (int k = 0; k < vertices.size(); k++) {
                for (int i = 0; i < vertices.size(); i++) {
                    for (int j = 0; j < vertices.size(); j++) {
                        if((dist[i][k] == Integer.MAX_VALUE || dist[k][j] == Integer.MAX_VALUE))
                            continue; //  No edge between these two
                        else {
                            if(dist[i][k] == Integer.MIN_VALUE || dist[k][j] == Integer.MIN_VALUE) {
                                dist[i][j] = Integer.MIN_VALUE; // Infinitely small edge
                            } else if(dist[i][k] + dist[k][j] < dist[i][j]){
                                dist[i][j] = dist[i][k] + dist[k][j]; // New, better path
                            }


                        }
                    }
                    if(dist[i][i] < 0){
                        propagateNegativeCycle(dist, graph, i);
                    }

                }
            }



        return dist;
    }

    public static void propagateNegativeCycle(int[][] dist, Graph<Void, Weight> graph, int index){
            dist[index][index] = Integer.MIN_VALUE;
            for (Edge<Void, Weight> edge : graph.getVertices().get(index).getEdges()) {
                propagateNegativeCycle(dist, graph, edge.getTo().getId(), index);
            }
    }
    public static void propagateNegativeCycle(int[][] dist, Graph<Void, Weight> graph, int index, int prevIndex) {
        if(dist[prevIndex][index] > Integer.MIN_VALUE){
            dist[prevIndex][index] = Integer.MIN_VALUE;
            for (Edge<Void, Weight> edge : graph.getVertices().get(index).getEdges()) {
                propagateNegativeCycle(dist, graph, edge.getTo().getId(), index);
            }
        }
    }
}
