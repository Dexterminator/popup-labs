package se.dxtr.maxflow;

import se.dxtr.graphlibrary.*;

/**
 * Created by dexter on 08/10/15.
 */
public class Main {
    static Kattio io = new Kattio (System.in, System.out);

    public static void main (String[] args) {
        int n = io.getInt ();
        int m = io.getInt ();
        int s = io.getInt ();
        int t = io.getInt ();

        Graph<FlowData> graph = new Graph<> (n);
        for (int i = 0; i < m; i++) {
            int u = io.getInt ();
            int v = io.getInt ();
            int c = io.getInt ();
            Edge<FlowData> edge = graph.addDirectedEdge (u, v, new FlowData (c));
            graph.getEdges ().stream ()
                    .filter (e -> e.getFrom ().getId () == v && e.getTo ().getId () == u)
                    .findFirst ()
                    .ifPresent (e -> {
                        edge.getData ().setReverseEdge (e);
                        e.getData ().setReverseEdge (edge);
                    });
        }

        graph.getEdges ().stream ()
                .filter (e -> e.getData ().getReverseEdge () == null)
                .forEach (e -> e.getData ().setReverseEdge (new Edge<> (e.getTo (), e.getFrom (), new FlowData (0))));

        EdmondsKarp.MaxFlowResult maxFlowResult =
                EdmondsKarp.maxFlow (graph, graph.getVertices ().get (s), graph.getVertices ().get (t));

        io.println (n + " " + maxFlowResult.maxFlow + " " + maxFlowResult.flowSolution.size ());
        for (Edge<FlowData> edge : maxFlowResult.flowSolution)
            io.println (edge.getFrom ().getId () + " " + edge.getTo ().getId () + " " + edge.getData ().getFlow ());
        io.close ();
    }
}
