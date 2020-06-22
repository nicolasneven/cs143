// This class contains several small examples of array manipulation.
// Each is written as a method.  The main method includes some
// sample client code.

import java.util.*;

public class ArrayProgramming {
   public static void main(String[] args) {
      int[] list1 = {8, 12, 4, 0, 208, 14, 42, 38, 72};
      int[] list2 = {42, 84, 92, 96, 2, 4, 8, 0, 72, 302, 17, 2, 408};
      int[] list3 = {-8, 0, 45, 103, 982};
      
      System.out.println("list1 = " + Arrays.toString(list1));
      System.out.println("list2 = " + Arrays.toString(list2));
      System.out.println("list3 = " + Arrays.toString(list3));
      
      System.out.println("isSorted(list1) returns " + isSorted(list1));
      System.out.println("isSorted(list2) returns " + isSorted(list2));
      System.out.println("isSorted(list3) returns " + isSorted(list3));

      System.out.println("result of rotating left:");
      System.out.println("    list1 = " + Arrays.toString(list1));
      for (int i = 0; i < list1.length; i++) {
         rotateLeft(list1);
         System.out.println("    list1 = " + Arrays.toString(list1));
      }
      
      System.out.println("Repeated for comparison:");
      System.out.println("list1 = " + Arrays.toString(list1));
      System.out.println("list2 = " + Arrays.toString(list2));
      System.out.println("list3 = " + Arrays.toString(list3));
      
      int[] result1 = interleave(list1, list2);
      int[] result2 = interleave(list2, list1);
      System.out.println("interleave(list1, list2) =");
      System.out.println(Arrays.toString(result1));
      System.out.println("interleave(list2, list1) =");
      System.out.println(Arrays.toString(result2));
   }
   
   // Write a static method called isSorted that takes an array of
   // integers as a parameter and that returns true if the integers
   // appear in sorted (nondecreasing) order
   public static boolean isSorted(int[] list) {
      for (int i = 0; i < list.length - 1; i++) {
         if (list[i] > list[i + 1]) {
            return false;
         }
      } 
      
      return true;
   }
   
   // Write a static method called rotateLeft that takes an array
   // of integers as a parameter and that rotates all values to the
   // left by one position, rotating the first value to the back of
   // the array.  For example, given the list {1, 2, 3, 4}, a call
   // on rotateLeft should yield {2, 3, 4, 1}.
   public static void rotateLeft(int[] list) {
      // make a temp
      int temp = list[0];
      
      // shift values
      for (int i = 0; i < list.length - 1; i++) {
         list[i] = list[i + 1];
      }
      
      
      // put temp at the end
      list[list.length - 1] = temp;
   }
   
   // Write a static method called interleave that takes two arrays
   // of integers as parameters and that returns a new array that
   // contains the result of interleaving the elements of the two
   // arrays.  Two arrays are interleaved by taking elements in an
   // alternating fashion from the two lists (first value of first
   // list, first value of second list, second value of first list,
   // second value of second list, etc).  If one list is longer
   // than the other, ignore the extra values.
   public static int[] interleave(int[] list1, int[] list2) {
      int minLength = Math.min(list1.length, list2.length);
      int[] result = new int[2 * minLength];
      
      for (int i = 0; i < minLength; i++) {
         // assign from list1
         // assign from list2
         result[2 * i] = list1[i];
         result[2 * i + 1] = list2[i];
      }
      return result;
   }
   
}
