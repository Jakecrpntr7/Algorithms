package com.algorithms.sort;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BubbleSorterTest {

    // Determine the count of random values to generate for test cases.
    private static final Integer VALUE_COUNT = 50;
    // Determine if a visual depiction should be printed to the console. If set to true, recommend
    // keeping VALUE_COUNT under 25.
    private static final boolean VISUALIZE = false;

    @Test
    void bubbleSorterIntegerTest() {
        ArrayList<Integer> values = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < VALUE_COUNT; ++i) {
            Integer value = random.nextInt();
            values.add(value);
        }
        BubbleSorter<Integer> sorter = new BubbleSorter<>(values, VISUALIZE);
        ArrayList<Integer> sortedValues = sorter.sort();
        for (int i = 1; i < sortedValues.size(); i++) {
            assertTrue(sortedValues.get(i) >= sortedValues.get(i-1));
        }
    }

    @Test
    void bubbleSorterDoubleTest() {
        ArrayList<Double> values = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < VALUE_COUNT; ++i) {
            Double value = random.nextDouble();
            values.add(value);
        }
        BubbleSorter<Double> sorter = new BubbleSorter<>(values, VISUALIZE);
        ArrayList<Double> sortedValues = sorter.sort();
        for (int i = 1; i < sortedValues.size(); i++) {
            assertTrue(sortedValues.get(i) >= sortedValues.get(i-1));
        }
    }

}
