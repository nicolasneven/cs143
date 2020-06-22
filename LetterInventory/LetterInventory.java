// Nicolas Neven
// 1/11/18
// CSE 143 AO
// TA: Benjamin Macmillan
// Assignment #1
//
// This program keeps track of an inventory of letters of the alphabet
// ignoring any non alphabetic characters

import java.util.*;
public class LetterInventory {
   
   private int[] result; // list of alphabetic counts
   private int size;     // sum of all counts in the list
   
   public static final int ALPHABET = 26; // size of alphabet and list
   
   // Pre : Takes in a string paramater
   // Post: Constructs a count of the alphabetic letters
   public LetterInventory(String data) {
      result = new int[ALPHABET];
      data = data.toLowerCase();
      for (int i = 0; i < data.length(); i++) {
         char c = data.charAt(i);
         if (c >= 'a' && c <= 'z') {
            result[c - 'a']++;
            size++;
         }
      }
   }
   
   // Post: Returns a sum of all the counts in the inventory
   public int size() {
      return size;
   }
   
   // Post: Returns a boolean depending on if the inventory is empty or not
   public boolean isEmpty() {
      return size == 0;
   }
   
   // Pre : Takes in a char and throws an IllegalArgumentException if
   // letter < 'a' or letter > 'z'
   // Post: Returns a count a how many there are of this letter in the inventory
   public int get(char letter) {
      letter = Character.toLowerCase(letter);
      if (letter < 'a' || letter > 'z') {
         throw new IllegalArgumentException();
      }
      return result[letter - 'a'];
   }
   
   // Post: Returns a string representation of the inventory with all letters
   // in lower case, sorted order, and surrounded by square brackets
   public String toString() {
      String s = "[";
      for (int i = 0; i < ALPHABET; i++) {
         for (int j = 0; j < result[i]; j++) {
            s += (char) ('a' + i);
         }
      }
      return s + "]";
   }
   
   // Pre : Takes in a char and a value and throws an IllegalArgumentException
   // if letter < 'a' or letter > 'z' or value < 0
   // Post: Sets the count for the given letter to the given value
   public void set(char letter, int value) {
      letter = Character.toLowerCase(letter);
      if (letter < 'a' || letter > 'z' || value < 0) {
         throw new IllegalArgumentException();
      }
      size = size - result[letter - 'a'] + value;
      result[letter - 'a'] = value;
   }
   
   // Pre : Takes in a LetterInventory object
   // Post: Returns a new LetterInventory object that represents the sum of
   // this LetterInventory and the other given LetterInventory
   public LetterInventory add(LetterInventory other) {
      return addSubtract(other, 1);
   }
   
   // Pre : Takes in a LetterInventory object
   // Post: Returns a new LetterInventory object that represents the difference
   // of this LetterInventory and the other given LetterInventory, or null if
   // resulting count is less than 0
   public LetterInventory subtract(LetterInventory other) {
      return addSubtract(other, -1);
   }
   
   // Pre : Takes in a LetterInventory object and an integer
   // Post: Performs an operation on this LetterInventory and the other given
   // LetterInventory and returns a new LetterInventory object
   private LetterInventory addSubtract(LetterInventory other, int n) {
      LetterInventory total = new LetterInventory("");
      for (int i = 0; i < ALPHABET; i++) {
         total.result[i] = this.result[i] + (n * other.result[i]);
         if (total.result[i] < 0) {
            return null;
         }
         total.size += total.result[i];
      }
      return total;
   }
}