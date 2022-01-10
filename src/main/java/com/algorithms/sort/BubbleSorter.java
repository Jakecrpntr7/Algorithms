package com.algorithms.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.apache.commons.lang3.StringUtils;

/**
 * Time Complexity: O(n^2)
 * Bubble sort is an academic sorting algorithm that sorts by bubbling values into their
 * correct places. This should not be reviewed extensively as it has no practical purpose.
 */
public class BubbleSorter<T extends Comparable<? super T>> {
    ArrayList<T> data;
    boolean visualize = false;

    public BubbleSorter(T[] data, boolean visualize) {
        this.data = new ArrayList<>(Arrays.asList(data));
        this.visualize = visualize;
    }

    public BubbleSorter(ArrayList<T> data, boolean visualize) {
        this.data = data;
        this.visualize = visualize;
    }

    /**
     * Performs Bubble Sort on the class data.
     * @return ArrayList in sorted order as indicated by comparator.
     */
    public ArrayList<T> sort() {
        // No values to sort or list trivially sorted by nature of having 1 element.
        if (data == null || data.size() < 2) return data;

        // Iterate over the Array from the back to ensure each element is visited. On each iteration, values
        // in outer counter position onward will be sorted.
        for (int outerLoopCounter = data.size(); outerLoopCounter > 1; outerLoopCounter--) {
            // Prints the current array to the console without highlighting if enabled.
            if (visualize) {
                printArray(-1, -1);
            }
            // Inside loop iterates over elements prior to outer counter and pushes the larger values towards
            // the back of the array. On each outer loop it will move the largest value that has not yet been
            // placed into sorted order in sorted order.
            for (int innerLoopCounter = 0; innerLoopCounter < outerLoopCounter - 1; innerLoopCounter++) {
                if(data.get(innerLoopCounter).compareTo(data.get(innerLoopCounter+1)) > 0){
                    // Print the values to be swapped to the console to make the progression easier to understand if enabled.
                    if (visualize) {
                        System.out.println("Swapping: " + data.get(innerLoopCounter) + " <-> " + data.get(innerLoopCounter + 1));
                    }
                    Collections.swap(data, innerLoopCounter, innerLoopCounter+1);
                    // Print the new array ordering to the console highlighting the changed elements if enabled.
                    if (visualize) {
                        printArray(innerLoopCounter+1, innerLoopCounter);
                    }
                }
            }
        }
        return data;
    }

    /**
     * Helper function to print the current data array to the console for visualization.
     * @param greenIndex Index of element to highlight value green.
     * @param redIndex Index of element to highlight value red.
     */
    private void printArray(int greenIndex, int redIndex) {

        // ANSI codes to override color in terminal (If supported).
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";

        // Break to separate data visually.
        System.out.println(StringUtils.repeat('_', 50));

        // Print the array with any colorings.
        for(int i = 0; i < data.size(); i++) {
            if (i == greenIndex) {
                System.out.print(ANSI_GREEN + data.get(i) + ANSI_RESET + " ");
            }
            else if (i == redIndex) {
                System.out.print(ANSI_RED + data.get(i) + ANSI_RESET + " ");
            }
            else System.out.print(data.get(i) + " ");
        }
        System.out.println();

        // Break to separate data visually.
        System.out.println(StringUtils.repeat('_', 50));
    }

}
