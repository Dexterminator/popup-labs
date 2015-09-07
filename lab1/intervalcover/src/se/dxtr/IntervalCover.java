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
        /*
        Interval best = new Interval (Float.MAX_VALUE, Float.MIN_VALUE, Integer.MIN_VALUE);
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
        */
        if(intervals[0].high < goal.high){
            return null;
        }
        double prev;
        double start = goal.low;
        if(goal.low == goal.high){
            swag : while(start <= goal.high){
                prev = start;
                for (Interval interval: intervals){
                    if(interval.low <= start){
                        solution.add(interval.index);
                        start = interval.high;

                        if(interval.high >= goal.high){
                            break swag;
                        }
                        break;
                    }
                }
                if(prev == start){
                    return null;
                }

            }
            return solution;
        }

        while(start < goal.high){
            prev = start;
            for (Interval interval: intervals){
                if(interval.low <= start){
                    solution.add(interval.index);
                    start = interval.high;
                    break;
                }
            }
            if(prev == start){
                return null;
            }

        }
        return solution;
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
        /*
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
        */
    }

    private static int[] getSolutionArray (List<Interval> solution) {
        int[] solutionArray = new int[solution.size ()];
        for (int i = 0; i < solution.size (); i++)
            solutionArray[i] = solution.get (i).index;
        return solutionArray;
    }

    public static class Interval implements Comparable<Interval> {
        public final double low;
        public final double high;
        public final int index;

        public Interval (double low, double high, int index) {
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
            if (high == other.high)
                return 0;
            return high > other.high ? -1 : 1;
        }
    }
}
