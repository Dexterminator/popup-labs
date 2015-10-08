package se.dxtr.maxflow;

import se.dxtr.graphlibrary.FlowData;
import se.dxtr.graphlibrary.Graph;
import se.dxtr.graphlibrary.Kattio;

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
            graph.addEdge (u, v, new FlowData (c));
        }
        
        io.println (graph);
        io.close ();
    }
}
