package se.dxtr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dexter on 09/09/15.
 */
public class LIS {

    public static final int NEGATIVE_INFINITY = Integer.MIN_VALUE;
    public static final int INFINITY = Integer.MAX_VALUE;

    public static List<Integer> lis (Integer[] objects) {
        Integer[] last = new Integer[objects.length + 1];
        last[0] = NEGATIVE_INFINITY;
        for (int i = 1; i < last.length; i++)
            last[i] = INFINITY;

        for (int i = 0; i < objects.length; i++) {
            int largestSmallerThanCurrent = NEGATIVE_INFINITY;
            System.out.println ("Last for " + i + " " + Arrays.toString(last));
            for (int l = 0; l <= i; l++) {
                if (last[l] < objects[i] && l >= largestSmallerThanCurrent) {
                    largestSmallerThanCurrent = l;
                }
            }
            last[largestSmallerThanCurrent + 1] = Math.min (objects[i], last[largestSmallerThanCurrent + 1]);
        }

        System.out.println (Arrays.toString (last));
        List<Integer> lis = new ArrayList<Integer> ();
        for (int i = 1; i < last.length; i++) {
            if (last[i] == INFINITY)
                break;
            lis.add (last[i]);
        }
        return lis;
    }
}
