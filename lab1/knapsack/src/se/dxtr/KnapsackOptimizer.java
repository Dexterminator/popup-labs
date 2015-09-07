package se.dxtr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dexter on 07/09/15.
 */
public class KnapsackOptimizer {

    public static List<Integer> knapsack (double realCapacity, Item[] items) {
        int capacity = (int) realCapacity;
        int[][] values = new int[items.length + 1][capacity+1];

        for (int i = 1; i < values.length; i++) {
            for (int j = 0; j < capacity + 1; j++) {
                int itemWeight = items[i - 1].weight;
                int prevMaxValue = values[i - 1][j];
                if (itemWeight <= j) {
                    int itemValue = items[i - 1].value;
                    values[i][j] = Math.max (prevMaxValue, values[i-1][j- itemWeight] + itemValue);
                } else {
                    values[i][j] = prevMaxValue;
                }
            }
        }

        List<Integer> chosenItems = new ArrayList<> ();
        int currValue = values[items.length][capacity];
        int itemIndex = items.length;
        int weightIndex = capacity;
        while (currValue != 0) {
            if (currValue != values[itemIndex - 1][weightIndex]) {
                chosenItems.add (itemIndex-1);
                weightIndex -= items[itemIndex-1].weight;
            }
            itemIndex--;
            currValue = values[itemIndex][weightIndex];
        }
        return chosenItems;
    }

    public static class Item {
        public final int value;
        public final int weight;

        public Item (int value, int weight) {

            this.value = value;
            this.weight = weight;
        }
    }
}
