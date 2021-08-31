package bst_test;

import static org.junit.jupiter.api.Assertions.*;

import bst.BinarySearchTree;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BinarySearchTreeTest {

  BinarySearchTree<Integer> treeInt;
  BinarySearchTree<String> treeString;

  @BeforeEach
  void setUp() {
    treeInt = new BinarySearchTree<>();
    treeString = new BinarySearchTree<>();
  }

  @AfterEach
  void tearDown() {
    treeInt = null;
    treeString = null;
  }

  @Test
  void testHeightEmpty() {
    assertEquals(-1, treeInt.height());
    assertEquals(-1, treeString.height());
  }

  @Test
  void testHeightOne() {
    treeInt.add(10);
    treeString.add("Test");

    assertEquals(0, treeInt.height());
    assertEquals(0, treeString.height());
  }

  @Test
  void testHeightTwo() {
    treeInt.add(10);
    treeInt.add(5);

    treeString.add("Testar höjd 1");
    treeString.add("Testtest");

    assertEquals(1, treeInt.height());
    assertEquals(1, treeString.height());
  }

  @Test
  void testAdd10Increments() {
    // Assert add returns true - add works
    for (int i = 0; i < 10; i++) {
      assertTrue(treeInt.add(i));
      assertTrue(treeString.add(String.valueOf(i)));
    }

    assertEquals(3, treeInt.height());
    assertEquals(3, treeString.height());

    assertEquals(10, treeInt.size());
    assertEquals(10, treeString.size());

  }

  @Test
  void testAddBalanced() {
    treeInt.add(2);
    treeInt.add(1);
    treeInt.add(3);
    assertEquals(1, treeInt.height());
    assertEquals(3, treeInt.size());
    assertTrue(treeInt.isBalanced());

    treeInt.add(4);
    treeInt.add(5);
    assertEquals(2, treeInt.height());
    assertEquals(5, treeInt.size());
    assertTrue(treeInt.isBalanced());

    treeString.add("bac");
    treeString.add("abc");
    treeString.add("cab");
    assertEquals(1, treeString.height());
    assertEquals(3, treeString.size());
    assertTrue(treeString.isBalanced());

    treeString.add("daba");
    treeString.add("eaba");
    assertEquals(2, treeString.height());
    assertEquals(5, treeString.size());
    assertTrue(treeString.isBalanced());
  }

  @Test
  void testAddUnbalanced() {
    treeInt.add(5);
    assertTrue(treeInt.isBalanced());
    treeInt.add(4);
    assertTrue(treeInt.isBalanced());
    treeInt.add(3);
    assertFalse(treeInt.isBalanced());
  }

  @Test
  void testAddSame() {
    treeInt.add(1);
    assertFalse(treeInt.add(1));
    assertEquals(1, treeInt.size());
    assertEquals(0, treeInt.height());
  }

  @Test
  void testClear() {
    for (int i = 0; i < 10; i++) {
      treeInt.add(i);
      treeString.add(String.valueOf(i));
    }
    treeInt.clear();
    treeString.clear();

    assertEquals(-1, treeInt.height());
    assertEquals(-1, treeString.height());

    assertEquals(0, treeInt.size());
    assertEquals(0, treeString.size());
  }
}
