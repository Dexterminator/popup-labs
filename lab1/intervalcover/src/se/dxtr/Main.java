package se.dxtr;

import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        while (io.hasMoreTokens()) {
            IntervalCover.Interval goal = new IntervalCover.Interval (io.getDouble(), io.getDouble(), Integer.MIN_VALUE);
            int n = io.getInt ();
            IntervalCover.Interval[] intervals = new IntervalCover.Interval[n];
            for (int i = 0; i < n; i++) {
                intervals[i] = new IntervalCover.Interval (io.getDouble(), io.getDouble(), i);
            }
            List<Integer> solution = IntervalCover.intervalCover (goal, intervals);
            if (solution != null) {
                String collect = solution.stream().map (String::valueOf).collect (Collectors.joining (" "));
                io.println (solution.size ());
                io.println (collect);
            } else {
                io.println ("impossible");
            }
            io.flush();
        }
        io.close();
    }

}
