package se.dxtr;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class that provides a method for solving the knapsack problem.
 *
 * Authors:
 * Dexter Gramfors, Ludvig Jansson
 */
public class KnapsackOptimizer {

    /**
     * Solves the knapsack problem by finding a selection of items such that the value of the items is maximized
     * and their total weight is less than the capacity of the knapsack.
     *
     * @param realCapacity The weight capacity of the knapsack
     * @param items An array of the available items that can be packed into the knapsack
     * @return A list of indices of the items to pack in order to achieve the maximum possible value while
     * fulfilling the requirement that their total weight is less than or equal to the capacity.
     */
    public static List<Integer> knapsack (double realCapacity, Item[] items) {
        int capacity = (int) realCapacity;
        int[][] values = new int[items.length + 1][capacity+1];

        for (int i = 1; i < values.length; i++) {
            for (int j = 0; j < capacity + 1; j++) {
                int itemWeight = items[i - 1].weight;
                int prevMaxValue = values[i - 1][j];
                if (itemWeight <= j) {
                    int itemValue = items[i - 1].value;
                    values[i][j] = Math.max (prevMaxValue, values[i-1][j-itemWeight] + itemValue);
                } else {
                    values[i][j] = prevMaxValue;
                }
            }
        }

        List<Integer> chosenItems = findChosenItems (items, capacity, values);
        return chosenItems;
    }

    /**
     * Finds the indices of the items to include in the solution by backtracking through the matrix.
     */
    private static List<Integer> findChosenItems (Item[] items, int capacity, int[][] values) {
        List<Integer> chosenItems = new ArrayList<> ();
        int currValue = values[items.length][capacity];
        int itemIndex = items.length;
        int weightIndex = capacity;
        while (currValue != 0) {
            boolean itemAdded = currValue != values[itemIndex - 1][weightIndex];
            if (itemAdded) {
                chosenItems.add (itemIndex-1);
                weightIndex -= items[itemIndex-1].weight;
            }
            itemIndex--;
            currValue = values[itemIndex][weightIndex];
        }
        return chosenItems;
    }

    /**
     * Data class that represents an item by its value and its weight.
     */
    public static class Item {
        public final int value;
        public final int weight;

        public Item (int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }
}
