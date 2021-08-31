# Auto balancing BST implementation

## Description

The project consists of a basic auto balancing binary search tree, developed as an extension of a Java course assignment. The point of the auto balancing is to optimize searching in the tree, at the cost of a slower add method due to the constant rebalancing.

>The goal was solely to practice data structures and recursive algorithms, not to build an entirely functional fully implemented data structure that could be used for any sort of production **(it most definitely shouldn't be)**.

&nbsp;

## Implemented functionality

The following key methods are implemented:

- `add`: add new nodes
- `clear`: empty the tree completely
- `size`: retrieve the size (number of elements)
- `height`: calculate the height
- `contains`: determines if a value is present using binary search
- `isBalanced`: determines if the tree currently is balanced
- `rebuild`: rebuilds the tree in order to make it balanced
- `printInOrder`: prints the contents inorder

>Note that the implementation follows the convention that a single-noded BST has a height of 0.
