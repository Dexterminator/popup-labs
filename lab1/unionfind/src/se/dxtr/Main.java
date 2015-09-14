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
//        DisjointSets disjointSets = new DisjointSets (100);
//        System.out.println ("set 1");
//        System.out.println (disjointSets.inSameSet (1, 3));
//        disjointSets.union (1, 8);
//        disjointSets.union (3, 8);
//        System.out.println (disjointSets.inSameSet (1, 3));
//
//        System.out.println ("Set 2");
//        DisjointSets disjointSets2 = new DisjointSets (100);
//        disjointSets2.union (0, 1);
//        disjointSets2.union (2, 3);
//        disjointSets2.union (4, 5);
//        disjointSets2.union (6, 7);
//        disjointSets2.union (8, 9);
//        disjointSets2.union (1, 9);
//        disjointSets2.union (2, 9);
//        System.out.println (disjointSets2.inSameSet (2, 9));
//        System.out.println (disjointSets2.inSameSet (0, 1));
    }
}
