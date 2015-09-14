package se.dxtr;

public class Main {

    public static void main(String[] args) {
        Kattio io = new Kattio (System.in, System.out);
        int n = io.getInt ();
        int q = io.getInt ();
        FenwickTree fenwickTree = new FenwickTree (n);

        for (int i = 0; i < q; i++) {
            String operation = io.getWord ();
            int index = io.getInt ();
            if (operation.equals ("+")) {
                int delta = io.getInt ();
                fenwickTree.add (index, delta);
            } else {
                // Operation equals "?"
                io.println (fenwickTree.sum (index));
            }
        }
        io.flush ();
        io.close ();
    }
}
