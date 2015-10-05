package se.dxtr;

import java.util.ArrayList;
import java.util.List;

public class Main {

    static Kattio io = new Kattio (System.in, System.out);

    public static void main (String[] args) {
        while (io.hasMoreTokens ()) {
            int n = io.getInt ();
            int m = io.getInt ();
            List<Vertex> vertices = new ArrayList<> ();
            for (int i = 0; i < n; i++) {
                int u = io.getInt ();
                int v = io.getInt ();
                int w = io.getInt ();
            }
        }
    }
}
