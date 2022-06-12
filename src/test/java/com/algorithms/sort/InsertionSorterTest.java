package com.algorithms.sort;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InsertionSorterTest {
    // Determine the count of random values to generate for test cases.
    private static final Integer VALUE_COUNT = 50;

    @Test
    void insertionSorterIntegerTest() {
        ArrayList<Integer> values = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < VALUE_COUNT; ++i) {
            Integer value = random.nextInt();
            values.add(value);
        }
        InsertionSorter<Integer> sorter = new InsertionSorter<>(values);
        ArrayList<Integer> sortedValues = sorter.sort();
        for (int i = 1; i < sortedValues.size(); i++) {
            assertTrue(sortedValues.get(i) >= sortedValues.get(i-1));
        }
    }
}
