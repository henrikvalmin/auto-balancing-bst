package bst;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinarySearchTree<E extends Comparable<E>> {

    private BinaryNode<E> root;
    private Comparator<E> comparator;
    private int size;

    /**
     * Constructs an empty binary search tree, assuming E extends Comparable.
     */
    public BinarySearchTree() {
        this.comparator = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
        this.size = 0;
    }

    /**
     * Inserts the value of data in the correct position in the binary search tree.
     * Duplicate values will be ignored.
     * 
     * @param data The value to be added
     * @return true if the value was added, otherwise false
     */
    public boolean add(E data) {
        if (root == null) {
            root = new BinaryNode<>(data);
            size++;
            return true;
        } else {
            boolean added = recAdd(root, data);
            if (added && isBalanced()) {
                rebuild();
            }
            return added;
        }
    }

    /**
     * Recursive auxillary method for add.
     */
    private boolean recAdd(BinaryNode<E> node, E data) {
        // All null checks have been made before this
        int compResult = comparator.compare(data, node.data);

        // Left
        if (compResult < 0) {
            // Found correct insertion spot
            if (node.left == null) {
                node.left = new BinaryNode<>(data);
                size++;
                return true;
                // Keep looking recursively in left subtree
            } else {
                return recAdd(node.left, data);
            }
        }
        // Right
        else if (compResult > 0) {
            // Found correct insertion spot
            if (node.right == null) {
                node.right = new BinaryNode<>(data);
                size++;
                return true;
                // Keep looking recursively in right subtree
            } else {
                return recAdd(node.right, data);
            }
        }
        return false;
    }

    /**
     * Calculates the height of the binary search tree. Follows the convention that
     * an empty tree has height -1 and a tree with only a root node has height 0.
     * 
     * @return The height of the binary search tree.
     */
    public int height() {
        return -1 + recHeight(root);
    }

    /**
     * Recursive auxillary method for height.
     */
    private int recHeight(BinaryNode<E> node) {
        if (node != null) {
            int leftHeight = recHeight(node.left);
            int rightHeight = recHeight(node.right);
            return 1 + Math.max(leftHeight, rightHeight);
        }
        return 0;
    }

    /**
     * Returns the number of elements in the binary search tree.
     */
    public int size() {
        return size;
    }

    /**
     * Clears the binary search tree.
     */
    public void clear() {
        this.size = 0;
        this.root = null;
    }

    /**
     * Prints the binary search tree inorder.
     */
    public void printInOrder() {
        List<E> inOrderedContent = new ArrayList<E>();
        toList(inOrderedContent, root);
        for (E data : inOrderedContent) {
            System.out.println(data);
        }
    }

    /**
     * Determines if the tree is balanced.
     * 
     * @return true if the tree is balanced, otherwise false
     */
    public boolean isBalanced() {
        return isBalanced(root);
    }

    /**
     * Recursive auxillary method for isBalanced(). Determines if the subtree from
     * the current node are balanced
     * 
     * @param node The current node
     * @return true if the subtree is balanced, otherwise false
     */
    private boolean isBalanced(BinaryNode<E> node) {
        if (node == null) {
            return true;
        }
        /*
         * The tree is balanced if the difference between the heights of the subtrees is
         * 1 and both subtrees are balanced
         */
        return diffIsMaxOne(recHeight(node.left), recHeight(node.right)) && isBalanced(node.left)
                && isBalanced(node.right);
    }

    /**
     * Determines if the absolute difference between two numbers a and b are at most
     * 1
     * 
     * @param a the first number
     * @param b the second number
     * @return true if the absolute difference is <= 1, otherwise false
     */
    private boolean diffIsMaxOne(int a, int b) {
        return Math.abs(a - b) <= 1;
    }

    /**
     * Determines if the tree contains the value o
     * 
     * @param o The value to check for
     * @return true if the tree contains the value, otherwise false
     */
    public boolean contains(E o) {
        return recContains(root, o);
    }

    /**
     * Recursive auxillary method for contains(E o). Determines if the current node
     * has the value of o, and if not, which subtree to keep searching
     * 
     * @param node
     * @param o
     * @return
     */
    private boolean recContains(BinaryNode<E> node, E o) {
        if (node == null) {
            return false;
        }
        int compResult = comparator.compare(o, node.data);
        if (compResult == 0) {
            return true;
        } else if (compResult < 0) {
            return recContains(node.left, o);
        } else {
            return recContains(node.right, o);
        }
    }

    /**
     * Rebuilds the tree in order to balance it.
     */
    public void rebuild() {
        ArrayList<E> dataList = new ArrayList<>();
        toList(dataList, root);
        root = buildTree(dataList, 0, dataList.size());
    }

    /**
     * Adds the contents of the tree inorder from the current node node
     * 
     * @param list The list that content is added to
     * @param node The current node to iterate from
     */
    private void toList(List<E> list, BinaryNode<E> node) {
        if (node != null) {
            toList(list, node.left);
            list.add(node.data);
            toList(list, node.right);
        }
    }

    /**
     * Rebuilds the tree from the List list
     * 
     * @param list  The List to build from
     * @param first The first index of the list section that currently is being
     *              added
     * @param last  The last index of the list section that currently is being added
     * @return
     */
    private BinaryNode<E> buildTree(List<E> list, int first, int last) {
        BinaryNode<E> subRoot = null;

        // Recursive calls until indices moved past each other -> found position
        if (last - first > 0) {
            int mid = first + (last - first) / 2;
            subRoot = new BinaryNode<>(list.get(mid));
            subRoot.left = buildTree(list, first, mid);
            subRoot.right = buildTree(list, mid + 1, last);
        }
        return subRoot;
    }

    static class BinaryNode<E> {

        private E data;
        private BinaryNode<E> left;
        private BinaryNode<E> right;

        private BinaryNode(E data) {
            this.data = data;
        }
    }
}
