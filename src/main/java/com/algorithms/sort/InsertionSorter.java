package com.algorithms.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


/**
 * Time Complexity: O(N^2). Runs best on nearly sorted datasets.
 * Insertion sort works by starting at the second value (as the first is trivially sorted) and maintaining
 * list to the left of the outer index that is sorted. On each iteration it takes the current value and
 * places it in sorted position within the sub-list to the left of the outer index.
 */
public class InsertionSorter <T extends Comparable<? super T>>{

    ArrayList<T> data;

    public InsertionSorter(T[] data) {
        this.data = new ArrayList<>(Arrays.asList(data));
    }

    public InsertionSorter(ArrayList<T> data) {
        this.data = data;
    }

    /**
     * Performs insertion sort to sort data property.
     * Insertion sort maintains a list to left of the outer index with values that are currently sorted
     * and gradually pushes the outer index towards the end of the collection.
     * @return Data sorted as an ArrayList<T>
     */
    public ArrayList<T> sort() {
        for (int outerIndex = 1; outerIndex < this.data.size(); outerIndex++) {
            for (int innerIndex = outerIndex; innerIndex > 0 && this.isLess(innerIndex, innerIndex-1); innerIndex--) {
                Collections.swap(this.data, innerIndex, innerIndex-1);
            }
        }
        return this.data;
    }

    /**
     * Helper function to determine if the value at the provided index is less than the compared index.
     * @param index containing value to determine if it is larger.
     * @param compareIndex containing value to determine if it is smaller.
     * @return boolean indicating comparison result.
     */
    private boolean isLess(int index, int compareIndex) {
        return this.data.get(index).compareTo(this.data.get(compareIndex)) < 0;
    }

}
