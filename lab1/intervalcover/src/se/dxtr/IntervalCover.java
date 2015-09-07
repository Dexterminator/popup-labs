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
        Interval best = new Interval (Float.MAX_VALUE, Float.MIN_VALUE, Integer.MIN_VALUE);
        List<Integer> solution = new ArrayList<> ();
        int bestIndex = Integer.MIN_VALUE;
        float highest = Float.MIN_VALUE;
        for (int i = 0; i < intervals.length; i++) {
            Interval interval = intervals[i];
            //highest = interval.high > highest ? interval.high : highest;
            if (interval.low <= goal.low && interval.high > best.high) {
                best = interval;
                bestIndex = i;
            }
            if (interval.low > goal.low) {
                break;
            }
        }
        if (bestIndex == Integer.MIN_VALUE)
            return null;

        solution.add (best.index);
        if (best.high >= goal.high)
            return solution;

        Interval currentBest = best;
        Interval currentBestImprovement = best;

        int currentIndex = bestIndex + 1;
        boolean improved;
//        while (currentBest.high < goal.high) {
//            improved = false;
//            for (int i = currentIndex; i < intervals.length; i++) {
//                if (intervals[i].low <= currentBest.high) {
//                    if (intervals[i].high > currentBestImprovement.high) {
//                        currentBestImprovement = intervals[i];
//                        currentIndex = i;
//                        improved = true;
//                        if (i == intervals.length - 1) {
//                            currentBest = currentBestImprovement;
//                            solution.add (currentBestImprovement);
//                        }
//                    }
//                } else {
//                    currentIndex = i;
//                    currentBest = currentBestImprovement;
//                    solution.add (currentBest);
//                    break;
//                }
//            }
//            if (!improved) {
//                return null;
//            }
//        }
//        return getSolutionArray (solution);

        for (int i = bestIndex; i < intervals.length; i++) {
            if (intervals[i].low <= currentBest.high) {
                if (intervals[i].high > currentBestImprovement.high) {
                    currentBestImprovement = intervals[i];
                }
            } else {
                solution.add (currentBestImprovement.index);
                if (i == intervals.length - 1) {
                    currentBest = currentBestImprovement;
                    if (intervals[i].low <= currentBest.high) {
                        if (intervals[i].high > currentBestImprovement.high) {
                            solution.add(i);
                            currentBestImprovement = intervals[i];
                        }
                    }
                }

                if (currentBestImprovement.high >= goal.high)
                    return solution;
                currentBest = currentBestImprovement;
//                if (intervals[i].high > currentBestImprovement.high)
//                    currentBestImprovement = intervals[i];
            }
        }

        return null;
    }

    private static int[] getSolutionArray (List<Interval> solution) {
        int[] solutionArray = new int[solution.size ()];
        for (int i = 0; i < solution.size (); i++)
            solutionArray[i] = solution.get (i).index;
        return solutionArray;
    }

    public static class Interval implements Comparable<Interval> {
        public final float low;
        public final float high;
        public final int index;

        public Interval (float low, float high, int index) {
            this.low = low;
            this.high = high;
            this.index = index;
        }

        @Override
        public String toString () {
            return "Interval{" +
                    "low=" + low +
                    ", high=" + high +
                    '}';
        }

        @Override
        public int compareTo (Interval other) {
            if (low == other.low)
                return 0;
            return low > other.low ? 1 : -1;
        }
    }
}
