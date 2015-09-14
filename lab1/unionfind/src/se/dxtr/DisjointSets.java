package se.dxtr;

/**
 * Created by dexter on 10/09/15.
 */
public class DisjointSets {
    private int[] ids;
    private int[] sizes;

    public DisjointSets (int n) {
        ids = new int[n];
        sizes = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
            sizes[i] = 1;
        }
    }

    public void union (int a, int b) {
        int aRoot = root (a);
        int bRoot = root (b);
        if (aRoot == bRoot)
            return;
        if (sizes[aRoot] < sizes[bRoot]) {
            ids[aRoot] = ids[bRoot];
            sizes[bRoot] += sizes[aRoot];
        } else {
            ids[bRoot] = ids[aRoot];
            sizes[aRoot] += sizes[bRoot];
        }
    }

    public boolean inSameSet (int a, int b) {
        return root (a) == root (b);
    }

    private int root (int a) {
        int curr = a;
        while (ids[curr] != curr) {
            ids[curr] = ids[ids[curr]];
            curr = ids[curr];
        }

//        int root = curr;
//        curr = a;
//        while (ids[curr] != curr) {
//            int beforeUpdate = curr;
//            ids[curr] = root;
//            curr = ids[beforeUpdate];
//        }

        return curr;
    }
}
