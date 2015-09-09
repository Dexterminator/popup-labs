package se.dxtr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dexter on 07/09/15.
 */
public class IntervalCover {

    public static List<Integer> intervalCover (Interval goal, Interval[] intervals) {
        Arrays.sort (intervals);
        List<Integer> solution = new ArrayList<> ();

        int i = 0;
        double leftBound = goal.left;
        do {
            Interval best = null;
            while (i < intervals.length && intervals[i].left <= leftBound) {
                if (best == null)
                    best = intervals[i];
                else if (intervals[i].right > best.right)
                    best = intervals[i];
                i++;
            }
            if (best == null)
                return null;
            solution.add (best.index);
            leftBound = best.right;
        } while (leftBound < goal.right);

        return solution;
    }

    public static class Interval implements Comparable<Interval> {
        public final double left;
        public final double right;
        public final int index;

        public Interval (double low, double high, int index) {
            this.left = low;
            this.right = high;
            this.index = index;
        }

        @Override
        public String toString () {
            return "Interval{" +
                    "left=" + left +
                    ", right=" + right +
                    '}';
        }

        @Override
        public int compareTo (Interval other) {
            return Double.compare (left, other.left);
        }
    }
}
