package se.dxtr;

import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Kattio io = new Kattio (System.in, System.out);
        while (io.hasMoreTokens ()) {
            double capacity = io.getDouble ();
            int n = io.getInt ();
            KnapsackOptimizer.Item[] items = new KnapsackOptimizer.Item[n];
            for (int i = 0; i < n; i++) {
                items[i] = new KnapsackOptimizer.Item (io.getInt (), io.getInt ());
            }
            List<Integer> chosenItems = KnapsackOptimizer.knapsack (capacity, items);
            io.println (chosenItems.size ());
            io.println (chosenItems.stream ().map (i -> i.toString ()).collect (Collectors.joining (" ")));
            io.flush ();
        }
        io.close ();
    }
}
