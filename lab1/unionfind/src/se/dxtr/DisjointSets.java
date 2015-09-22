package se.dxtr;

/**
 * Created by dexter on 10/09/15.
 */
public class DisjointSets {
    private int[] ids;
    private int[] heights;

    public DisjointSets (int n) {
        ids = new int[n];
        heights = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
            heights[i] = 1;
        }
    }

    public void union (int a, int b) {
        int aRoot = root (a);
        int bRoot = root (b);
        if (aRoot == bRoot)
            return;
        if (heights[aRoot] < heights[bRoot]) {
            ids[aRoot] = bRoot;
        } else if (heights[aRoot] > heights[bRoot]){
            ids[bRoot] = aRoot;
        } else { // heights equal, attaching the tree will increase height by 1
            ids[aRoot] = bRoot;
            heights[bRoot]++;
        }
    }

    public boolean inSameSet (int a, int b) {
        return root (a) == root (b);
    }

    private int root (int a) {
        if (a != ids[a])
            ids[a] = root (ids[a]);
        return ids[a];
    }
}
