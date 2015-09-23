package se.dxtr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utility class that solves the interval cover problem.
 *
 * Authors:
 * Dexter Gramfors, Ludvig Jansson
 */
public class IntervalCover {

    /**
     * Solves the interval cover problem given a goal interval to cover, and a list of available intervals
     * that can be used in the solution.
     * @param goal the goal interval that should be covered
     * @param intervals An array representing the selection of intervals that can be used in the cover solution
     * @return If a solution exists: A list of indexes of the intervals in the original array that cover the goal
     * interval and that is as small as possible
     * <br>If no solution exists: null
     */
    public static List<Integer> intervalCover (Interval goal, Interval[] intervals) {
        Arrays.sort (intervals);
        List<Integer> solution = new ArrayList<> ();

        int i = 0;
        double leftBound = goal.left;
        do {
            Interval best = null;
            while (i < intervals.length && intervals[i].left <= leftBound) {
                if (best == null || intervals[i].right > best.right)
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

    /**
     * Data class that represents an interval by its left bound, its right bound, and its original index.
     */
    public static class Interval implements Comparable<Interval> {
        public final double left;
        public final double right;
        public final int index;

        public Interval (double left, double right, int index) {
            this.left = left;
            this.right = right;
            this.index = index;
        }

        public Interval (double left, double right) {
            this.left = left;
            this.right = right;
            this.index = Integer.MIN_VALUE;
        }

        @Override
        public int compareTo (Interval other) {
            return Double.compare (left, other.left);
        }
    }
}
