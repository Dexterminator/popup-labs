package se.dxtr.allpairspath;

import se.dxtr.graphlibrary.*;

/**
 * Created by Ludde on 15-10-07.
 */
public class Main {
    static Kattio io;

    public static void main (String[] args) {
        io = new Kattio (System.in, System.out);
        while (io.hasMoreTokens ()) {
            int n = io.getInt ();
            int m = io.getInt ();
            int q = io.getInt ();

            if (n == 0 && m == 0 && q == 0)
                break;

            Graph<Void, Weight> graph = new Graph<> (n);
            for (int i = 0; i < m; i++) {
                int u = io.getInt ();
                int v = io.getInt ();
                int w = io.getInt ();
                graph.addDirectedEdge (u, v, new Weight (w));
            }
            FloydWarshall.AllPairsResult allPairsResult = FloydWarshall.shortestPath(graph);
            for (int i = 0; i < q; i++) {
                int from = io.getInt();
                int to = io.getInt();

                int dist = allPairsResult.dist[from][to];
                if(dist == Integer.MAX_VALUE)
                    io.println("Impossible");
                else if(from == to)
                    io.println(0);
                else
                    io.println(dist);

            }
            io.println ();
        }

        io.close ();
    }
}
