package se.dxtr;

public class Main {

    public static void main(String[] args) {
        DisjointSets disjointSets = new DisjointSets (100);
        System.out.println ("set 1");
        System.out.println (disjointSets.inSameSet (1, 3));
        disjointSets.union (1, 8);
        disjointSets.union (3, 8);
        System.out.println (disjointSets.inSameSet (1, 3));

        System.out.println ("Set 2");
        DisjointSets disjointSets2 = new DisjointSets (100);
        disjointSets2.union (0, 1);
        disjointSets2.union (2, 3);
        disjointSets2.union (4, 5);
        disjointSets2.union (6, 7);
        disjointSets2.union (8, 9);
        disjointSets2.union (1, 9);
        disjointSets2.union (2, 9);
        System.out.println (disjointSets2.inSameSet (2, 9));
        System.out.println (disjointSets2.inSameSet (0, 1));
    }
}
