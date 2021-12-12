package com.datastructures.trees;

/**
 * Enum to define the type of tree order being requested.
 * Preorder: Visits root, then left, then right
 * Inorder: Visits left, then root, then right
 * Postorder: Visits left, then root, then right.
 */
public enum TraversalType {
    PREORDER, INORDER, POSTORDER
}
