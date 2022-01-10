package com.datastructures.trees;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.ArrayDeque;

/**
 * This class implements a binary search tree data structure.
 * Binary search trees maintain the invariant each node to the left is smaller than the
 * current node and each node to the right is larger than the current node.
 * In this implementation, there are no duplicates.
 * @param <T> Comparable type.
 */
public class BinarySearchTree<T extends Comparable<T>> {

    /**
     * Constants to facilitate printing the tree.
     */
    private static final String PRINT_PADDING = "│  ";
    private static final String PRINT_RIGHT_CHILD_EDGE = "└──Right Child: ";
    private static final String PRINT_LEFT_CHILD_SIBLING_EDGE = "├──Left Child: ";
    private static final String PRINT_LEFT_CHILD_EDGE = "└──Left Child: ";

    /**
     * String constants to clarify comparator output.
     */
    private static final String LESS_THAN = "LESS_THAN";
    private static final String GREATER_THAN = "GREATER_THAN";
    private static final String EQUAL_TO = "EQUAL_TO";

    /**
     * The node class represents a single node within the BST.
     * This is declared within the BST class to cascade the comparable type T.
     */
    @Getter
    @Setter
    private class Node {
        T data;
        Node left;
        Node right;

        /**
         * Constructs a node with a left child, right child, and data member.
         * @param left the child with a data member smaller than the current node if one is provided.
         * @param right The child with a data member larger than the current node if one is provided.
         * @param data Comparable data value.
         */
        public Node(Node left, Node right, T data) {
            this.left = left;
            this.right = right;
            this.data = data;
        }

        /**
         * Attempts to add a child node maintaining the BST invariant.
         * @param node New node to be added.
         * @return True / False indicating whether the node was added.
         */
        public boolean addChild(Node node) {
            // Adds right child
            if (this.right == null && this.data.compareTo(node.data) < 0) {
                this.right = node;
                return true;
            }
            // Adds left child
            else if (this.left == null && this.data.compareTo(node.data) > 0) {
                this.left = node;
                return true;

            }
            // Child cannot be added while maintaining BST invariant
            return false;
        }

        /**
         * Returns the string representation of the given node.
         * @return String value of the data contents for the tree.
         */
        @Override
        public String toString() {
            return this.data.toString();
        }
    }

    @Getter
    private int size = 0;
    private Node root = null;

    /**
     * Indicates if any nodes exist in the BST.
     * @return Boolean indicating if the tree is empty.
     */
    public boolean isEmpty() {
        return getSize() == 0;
    }

    /**
     * Determines if a given value exists in the BST.
     * @param data the data to search.
     * @return boolean indicating if a node with the data is found.
     */
    public boolean contains(T data) {
        Node currentNode = root;
        while (currentNode != null && !hasData(currentNode, data)) {
            currentNode = data.compareTo(currentNode.data) > 0 ? currentNode.right : currentNode.left;
        }
        return hasData(currentNode, data);
    }

    /**
     * Helper function to determine if a node has a given value stored as data.
     * @param node the node under consideration.
     * @param data the data under consideration.
     * @return boolean indicating if the node's data matches the given data.
     */
    private boolean hasData(Node node, T data) {
        return node != null && node.data.compareTo(data) == 0;
    }

    /**
     * Adds the given data to the BST.
     * @param data to be added to tree.
     * @return boolean indicating if the data was added to the tree.
     */
    public boolean add(T data) {
        if (contains(data)) return false;
        if (root == null) return addRoot(data);
        // Traverse the BST to find the insertion point
        Node currentNode = root;
        Node previousNode = root;
        while (currentNode != null) {
            previousNode = currentNode;
            currentNode = (data.compareTo(currentNode.data) > 0 ? currentNode.right : currentNode.left);
        }
        if (previousNode.addChild(new Node(null, null, data))) {
            size++;
            return true;
        }
        return false;
    }

    /**
     * Helper function to add the root to an empty tree.
     * @param data value for the root node.
     * @return boolean indicating success or failure.
     */
    private boolean addRoot(T data) {
        if (root == null) {
            root = new Node(null, null, data);
            size++;
            return true;
        }
        return false;
    }

    /**
     * Removes the node with the provided data value from the BST.
     * @param data to be removed.
     * @return Boolean indicating success or failure of removal.
     */
    public boolean remove(T data) {
        if (contains(data)) {
            root = remove(root, data);
            size--;
            return true;
        }
        return false;
    }

    /**
     * Recursive removal function to locate and remove node.
     * @param node current node under consideration.
     * @param data value to be removed.
     * @return Recursively returns node to rebuild tree.
     */
    private Node remove(Node node, T data) {
        if (node == null) return null;
        String compareResult = comparatorToString(data.compareTo(node.data));
        switch(compareResult) {
            case LESS_THAN:
                node.left = remove(node.left, data);
                break;
            case GREATER_THAN:
                node.right = remove(node.right, data);
                break;
            case EQUAL_TO:
                if (node.left == null) {
                    return node.right;
                }
                else if (node.right == null) {
                    return node.left;
                }
                else {
                    Node tempNode = findMin(node.right);
                    node.data = tempNode.data;
                    node.right = remove(node.right, tempNode.data);
                }
        }
        return node;
    }

    /**
     * Locates the minimum node in the BST rooted at node.
     * @param node current node under consideration.
     * @return Node representing the minimum data value.
     */
    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    /**
     * Calculates the height of the BST.
     * @return int height of longest branch in tree.
     */
    public int height() {
        return height(root);
    }

    /**
     * Recursive helper function to traverse the BST returning the max of the subtree rooted at
     * the left child and the subtree rooted at the right child plus 1 for the current node.
     * @param node The current node under consideration.
     * @return integer value representing the height of the tree.
     */
    private int height(Node node) {
        if (node == null) return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * Helper Function to print the current tree.
     * @param printStream Output stream object to print string data.
     */
    public void printTree(PrintStream printStream) {
        StringBuilder sb = new StringBuilder();
        preOrderPrintTraversal(sb, "", "", this.root);
        printStream.print(sb.toString());
    }

    /**
     * Performs a preorder traversal to print tree structure. this may be
     * used as a visual verification of the tree.
     * @param sb Output value String builder to accumulate string.
     * @param padding left padding to be applied.
     * @param pointer Edge pointer in tree structure.
     * @param node Current node under consideration.
     */
    private void preOrderPrintTraversal(StringBuilder sb,  String padding, String pointer, Node node) {
        if (node == null) return;
        // Create the string for the current node. This consists of the padding from the left,
        // a pointer bar, the node value, and a newline to begin the next node.
        sb.append(padding + pointer + node + "\n");
        // Current padding begins string for next node at correct level.
        StringBuilder paddingBuilder = new StringBuilder(padding);
        // Add the padding symbol "|" to start the node.
        paddingBuilder.append(PRINT_PADDING);
        // Both children will get the base padding as the children exist at the same level.
        String paddingForChildren = paddingBuilder.toString();
        // Add lines to represent branches pointing to the children
        String branchForRight = PRINT_RIGHT_CHILD_EDGE;
        String branchForLeft = (node.right != null) ? PRINT_LEFT_CHILD_SIBLING_EDGE : PRINT_LEFT_CHILD_EDGE;
        // Process children
        preOrderPrintTraversal(sb, paddingForChildren, branchForLeft, node.left);
        preOrderPrintTraversal(sb, paddingForChildren, branchForRight, node.right);
    }

    /**
     * Helper function to get a string representation of comparator results. This is only
     * done for readability.
     * @param comparatorResult The integer returned by compareTo.
     * @return String constant representing relationship.
     */
    private String comparatorToString(int comparatorResult) throws IllegalArgumentException {
        switch(comparatorResult) {
            case -1:
                return LESS_THAN;
            case 0:
                return EQUAL_TO;
            case 1:
                return GREATER_THAN;
        }
        throw new IllegalArgumentException("Invalid comparator value provided");
    }

    /**
     * Helper function to route Binary Search Tree traversals.
     * Helpers that perform traversal could be combined but left separated for clarity.
     * @param type of traversal to perform (Preorder, Inorder, Postorder)
     * @return List of values retrieved from tree in ordering requested.
     * @throws IllegalArgumentException if an incorrect traversal is provided.
     */
    public List<T> traverse(TraversalType type) throws IllegalArgumentException {
        // Breaks aren't necessary on switch statement because returns make subsequent
        // cases unreachable
        switch(type) {
            case PREORDER:
                return preorderTraversal();
            case INORDER:
                return inorderTraversal();
            case POSTORDER:
                return postorderTraversal();
        }
        throw new IllegalArgumentException("Invalid traversal type provided");
    }

    /**
     * Performs an iterative in preorder traversal.
     * @return List of elements in order visited by preorder traversal.
     */
    private List<T> preorderTraversal() {
        List<T> result = new ArrayList<T>();
        if (root == null) return result;
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            result.add(currentNode.data);
            if (currentNode.left != null) {
                queue.offer(currentNode.left);
            }
            if (currentNode.right != null) {
                queue.offer(currentNode.right);
            }
        }
        return result;
    }

    /**
     * Performs an iterative in order traversal.
     * @return List of elements in order visited by in order traversal.
     */
    private List<T> inorderTraversal() {
        List<T> result = new ArrayList<T>();
        if (root == null) return result;
        Stack<Node> stack = new Stack<>();
        Node currentNode = root;
        while (currentNode != null || stack.size() > 0) {
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }
            currentNode = stack.pop();
            result.add(currentNode.data);
            currentNode = currentNode.right;
        }
        return result;
    }

    /**
     * Performs an iterative postorder traversal.
     * @return List of elements in order visited by postorder traversal.
     */
    private List<T> postorderTraversal() {
        List<T> result = new ArrayList<T>();
        if (root == null) return result;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        Node previousNode = null;
        while (!stack.isEmpty()) {
            Node currentNode = stack.peek();
            if (previousNode == null || previousNode.left == currentNode || previousNode.right == currentNode) {
                if (currentNode.left != null)
                    stack.push(currentNode.left);
                else if (currentNode.right != null)
                    stack.push(currentNode.right);
                else {
                    stack.pop();
                    result.add(currentNode.data);
                }
            }
            else if (currentNode.left == previousNode) {
                if (currentNode.right != null)
                    stack.push(currentNode.right);
                else {
                    stack.pop();
                    result.add(currentNode.data);
                }
            }
            else if (currentNode.right == previousNode) {
                stack.pop();
                result.add(currentNode.data);
            }
            previousNode = currentNode;
        }
        return result;
    }
}
