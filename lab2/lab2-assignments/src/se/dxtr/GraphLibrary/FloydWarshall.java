package se.dxtr.graphlibrary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dexter on 07/10/15.
 */
public class FloydWarshall {
    public static AllPairsResult shortestPath(Graph<Void, Weight> graph) {
        List<Vertex<Void, Weight>> vertices = graph.getVertices();
        List<Edge<Void, Weight>> edges = graph.getEdges();
        int[][] dist = new int[vertices.size()][vertices.size()];
//        Vertex[][] next = new Vertex[vertices.size()][vertices.size()];
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
//            next[edge.getFrom().getId()][edge.getTo().getId()] = edge.getTo();
        }
            for (int k = 0; k < vertices.size(); k++) {
                for (int i = 0; i < vertices.size(); i++) {
                    for (int j = 0; j < vertices.size(); j++) {
                        if((dist[i][k] == Integer.MAX_VALUE || dist[k][j] == Integer.MAX_VALUE))
                            continue;
                        else {
                            // TODO: Check if inf?
                            if(dist[i][k] == Integer.MIN_VALUE || dist[k][j] == Integer.MIN_VALUE) {
                                dist[i][j] = Integer.MIN_VALUE;
                            } else if(dist[i][k] + dist[k][j] < dist[i][j]){
                                dist[i][j] = dist[i][k] + dist[k][j];
                            }
                            if(i==j && dist[i][j] < 0){
//                                dist[i][j] = Integer.MIN_VALUE;

                                 propagateNegativeCycle(dist, graph, i);
//                    dist[i][i] = Integer.MIN_VALUE;
                            }
//                        next[i][j] = next[i][k];

                        }
                    }

                }
            }



        return new AllPairsResult(dist);
    }

    public static void propagateNegativeCycle(int[][] dist, Graph<Void, Weight> graph, int index){
//        if(dist[index][index] > Integer.MIN_VALUE){
            dist[index][index] = Integer.MIN_VALUE;
            for (Edge<Void, Weight> edge : graph.getVertices().get(index).getEdges()) {
                propagateNegativeCycle(dist, graph, edge.getTo().getId(), index);
            }
//        }
    }
    public static void propagateNegativeCycle(int[][] dist, Graph<Void, Weight> graph, int index, int prevIndex) {
        if(dist[prevIndex][index] > Integer.MIN_VALUE){
            dist[prevIndex][index] = Integer.MIN_VALUE;
            for (Edge<Void, Weight> edge : graph.getVertices().get(index).getEdges()) {
                propagateNegativeCycle(dist, graph, edge.getTo().getId(), index);
            }
        }
    }


        public static class AllPairsResult{
        public final int[][] dist;
//        public final Vertex[][] next;

        public AllPairsResult(int[][] dist){

            this.dist = dist;
//            this.next = next;
        }
//
//        public List<Integer> getPath(int from, int to){
//            if(next[from][to] == null)
//                return Collections.emptyList();
//            List<Integer> res = new ArrayList<>();
//            res.add(from);
//            while(from != to){
//                from = next[from][to].getId();
//                res.add(from);
//            }
//            return res;
//        }

    }
}
