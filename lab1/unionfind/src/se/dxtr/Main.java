package se.dxtr;

public class Main {

    public static void main(String[] args) {
        DisjointedSets disjointedSets = new DisjointedSets (100);
        System.out.println ("set 1");
        System.out.println (disjointedSets.inSameSet (1, 3));
        disjointedSets.union (1, 8);
        disjointedSets.union (3, 8);
        System.out.println (disjointedSets.inSameSet (1, 3));

        System.out.println ("Set 2");
        DisjointedSets disjointedSets2 = new DisjointedSets (100);
        disjointedSets2.union (0, 1);
        disjointedSets2.union (2, 3);
        disjointedSets2.union (4, 5);
        disjointedSets2.union (6, 7);
        disjointedSets2.union (8, 9);
        disjointedSets2.union (1, 9);
        disjointedSets2.union (2, 9);
        System.out.println (disjointedSets2.inSameSet (2, 9));
        System.out.println (disjointedSets2.inSameSet (0, 1));
    }
}
