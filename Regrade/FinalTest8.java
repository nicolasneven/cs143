// Program to test solutions to problem #8 on the cse143 final, winter 2018.
// Fill in your solution to limitLeaves, then compile and run the program

import java.util.*;

class IntTree {
   public void limitLeaves(int n) {
      overallRoot = limitLeaves(overallRoot, n);
   }
   
   private IntTreeNode limitLeaves(IntTreeNode node, int n) {
      if (node != null) {
         if (node.left == null && node.right == null && n >= node.data) {
            node = null;
         } else {
            node.left = limitLeaves(node.left, n);
            node.right = limitLeaves(node.right, n);
            node = limitLeaves(node, n);
         }
      }
      return node;
   }
   
   private IntTreeNode overallRoot;
   
   // this is the sample solution
   public void limitLeaves2(int min) {
      overallRoot = limitLeaves2(overallRoot, min);
   }
   
   private IntTreeNode limitLeaves2(IntTreeNode root, int min) {
      if (root != null) {
         root.left = limitLeaves2(root.left, min);
         root.right = limitLeaves2(root.right, min);
         if (root.left == null && root.right == null && root.data <= min) {
            root = null;
         }
      }
      return root;
   }
   
   // post: constructs an empty tree
   public IntTree() {
      overallRoot = null;
   }
   
   // pre : the tree satisfies the binary search tree property
   // post: value is added so as to preserve the binary search tree property
   public void add(int value) {
      overallRoot = add(overallRoot, value);
   }
   
   // pre : the tree with given root satisfies the binary search tree property
   // post: value is added so as to preserve the binary search tree property
   private IntTreeNode add(IntTreeNode root, int value) {
      if (root == null)
         root = new IntTreeNode(value);
      else if (value <= root.data)
         root.left = add(root.left, value);
      else
         root.right = add(root.right, value);
      return root;
   }
   
   // post: prints the data fields of the tree, one per line, following
   //       an inorder traversal and using indentation to indicate node depth;
   //       prints right to left so that it looks correct when the output is
   //       rotated.
   public void printStructure() {
      if (overallRoot == null) {
         System.out.println("empty tree");
      }
      printStructure(overallRoot, 0);
   }
   
   // post: prints in preorder the data fields of the given tree indenting
   //       each line to the given level
   private void printStructure(IntTreeNode root, int level) {
      if (root != null) {
         printStructure(root.right, level + 1);
         for (int i = 0; i < level; i++)
            System.out.print("    ");
         System.out.println(root.data);
         printStructure(root.left, level + 1);
      }
   }
   
   // returns true if this tree equals other tree
   public boolean equals(IntTree other) {
      return equals(overallRoot, other.overallRoot);
   }
   
   private boolean equals(IntTreeNode root1, IntTreeNode root2) {
      if (root1 == null || root2 == null)
         return root1 == null && root2 == null;
      else
         return root1.data == root2.data
      && equals(root1.left, root2.left)
      && equals(root1.right, root2.right);
   }
}

public class FinalTest8 {
   private static int testCount;
   private static int failCount;
   
   public static void main(String[] args) {
      for (int i = 0; i < 30; i++) {
         test(i);
      }
      if (failCount == 0) {
         System.out.println("passed all tests");
      } else {
         System.out.println("failed " + failCount + " of " + testCount +
         " tests");
      }
   }
   
   public static void test(int n) {
      IntTree t1 = new IntTree();
      IntTree t2 = new IntTree();
      Random r = new Random();
      for (int i = 0; i < n; i++) {
         int next = r.nextInt(100);
         t1.add(next);
         t2.add(next);
      }
      System.out.println("Testing tree with " + n + " nodes with min of 75:");
      t1.printStructure();
      System.out.println("Expected result:");
      t1.limitLeaves2(75);
      t1.printStructure();
      boolean fail = false;
      System.out.println("Actual result:");
      try {
         t2.limitLeaves(75);
      } catch (RuntimeException e) {
         int line = e.getStackTrace()[0].getLineNumber();
         System.out.println("    threw " + e + " at line #" + line);
         fail = true;
      }
      if (!fail) {
         t2.printStructure();
         if (!t1.equals(t2)) {
            fail = true;
         }
      }
      testCount++;
      if (fail) {
         System.out.println("failed");
         failCount++;
      } else {
         System.out.println("passed");
      }
      System.out.println("---------------------------------------------");
   }
}

class IntTreeNode {
   public int data;       // data stored at this IntTreeNode
   public IntTreeNode left;  // reference to left subtree
   public IntTreeNode right; // reference to right subtree
   
   // post: constructs a leaf node with given data
   public IntTreeNode(int data) {
      this(data, null, null);
   }
   
   // post: constructs a IntTreeNode with the given data and links
   public IntTreeNode(int data, IntTreeNode left, IntTreeNode right) {
      this.data = data;
      this.left = left;
      this.right = right;
   }
}
