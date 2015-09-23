package se.dxtr;

public class Main {

    public static void main(String[] args) {
        Kattio io = new Kattio (System.in, System.out);
        int n = io.getInt ();
        int q = io.getInt ();

        DisjointSets disjointSets = new DisjointSets (n);
        for (int i = 0; i < q; i++) {
            String operation = io.getWord ();
            int a = io.getInt ();
            int b = io.getInt ();
            if (operation.equals ("?")) {
                io.println (disjointSets.inSameSet (a, b) ? "yes" : "no");
            } else {
                // Word equals "="
                disjointSets.union (a, b);
            }
        }
        io.flush ();
        io.close ();
    }
}
