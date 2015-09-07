package se.dxtr;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner (System.in);
        while (s.hasNext ()) {
            IntervalCover.Interval goal = new IntervalCover.Interval (s.nextFloat (), s.nextFloat (), Integer.MIN_VALUE);
            int n = s.nextInt ();
            IntervalCover.Interval[] intervals = new IntervalCover.Interval[n];
            for (int i = 0; i < n; i++) {
                intervals[i] = new IntervalCover.Interval (s.nextFloat (), s.nextFloat (), i);
            }
            int[] solution = IntervalCover.intervalCover (goal, intervals);
            if (solution != null) {
                String collect = Arrays.stream (solution).mapToObj (idx -> String.valueOf (idx)).collect (Collectors.joining (" "));
                System.out.println (solution.length);
                System.out.println (collect);
            } else {
                System.out.println ("impossible");
            }

        }
    }

}
