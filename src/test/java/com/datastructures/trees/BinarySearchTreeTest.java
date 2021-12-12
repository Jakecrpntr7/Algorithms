package com.datastructures.trees;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Test class for Binary Search Tree.
 * This class utilizes random value generation to test the binary search tree is performing as expected.
 */
public class BinarySearchTreeTest {

    // Boolean for development only to verify the tree structure for debugging.
    private static final boolean VERIFY_VISUALLY = false;
    // Determine the count of random values to generate for test cases
    private static final Integer VALUE_COUNT = 50;

    @Test
    void binarySearchTreeIntegerAddRemoveContainsTest() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Random random = new Random();
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < VALUE_COUNT; ++i) {
            Integer value = random.nextInt();
            values.add(value);
            bst.add(value);
        }
        for (Integer value : values) assertTrue(bst.contains(value));
        if (VERIFY_VISUALLY) bst.printTree(System.out);
        for (Integer value : values) bst.remove(value);
        assertTrue(bst.isEmpty());
    }

    @Test
    void binarySearchTreePreOrderTraversalTest() {
        /*    Tree Structure
                    3
                  /    \
                 1      7
                       /  \
                      5    11
                             \
                             14
         */
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Integer[] values = {3, 7, 5, 11, 1, 14};
        Integer[] expectedResults = {3, 1, 7, 5, 11, 14};
        for (Integer value : values) bst.add(value);
        if (VERIFY_VISUALLY) bst.printTree(System.out);
        List<Integer> preOrderResults = bst.traverse(TraversalType.PREORDER);
        for (int i = 0; i < preOrderResults.size(); i++) assertEquals(expectedResults[i],preOrderResults.get(i));
    }

    @Test
    void binarySearchTreeInOrderTraversalTest() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        List<Integer> values = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < VALUE_COUNT; ++i) {
            Integer value = random.nextInt();
            values.add(value);
            bst.add(value);
        }
        List<Integer> inOrderResults = bst.traverse(TraversalType.INORDER);
        if (VERIFY_VISUALLY) bst.printTree(System.out);
        Collections.sort(values);
        for (int i = 0; i < inOrderResults.size(); i++) assertEquals(values.get(i),inOrderResults.get(i));
    }

    @Test
    void binarySearchTreePostOrderTraversalTest() {
        /*    Tree Structure
                    3
                  /    \
                 1      7
                       /  \
                      5    11
                             \
                             14
         */
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Integer[] values = {3, 7, 5, 11, 1, 14};
        Integer[] expectedResults = {1, 5, 14, 11, 7, 3};
        for (Integer value : values) bst.add(value);
        if (VERIFY_VISUALLY) bst.printTree(System.out);
        List<Integer> postOrderResults = bst.traverse(TraversalType.POSTORDER);
        for (int i = 0; i < values.length; i++) assertEquals(expectedResults[i],postOrderResults.get(i));
    }
}
