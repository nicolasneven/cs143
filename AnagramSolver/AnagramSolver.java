// Nicolas Neven
// 2/22/18
// CSE 143 AO
// TA: Benjamin Macmillan
// Assignment #6
//
// Uses a dictionary to find all combinations of words that have the same letters
// from a given phrase and prints the anagrams of that phrase.

import java.util.*;
public class AnagramSolver {

   private Map<String, LetterInventory> dictionary; // words linked to their LetterInventories
   private List<String> words;                      // list of dictionary words
   
   // Pre : Takes in a given list as its dictionary that is a nonempty collection of nonempty
   //       sequences of letters and contains no duplicates.
   // Post: Construcs a list of words mapped to their corresponding LetterInventories
   public AnagramSolver(List<String> list) {
      dictionary = new HashMap<String, LetterInventory>();
      this.words = list;
      for (String s : list) {
         LetterInventory word = new LetterInventory(s);
         dictionary.put(s, word);
      }
   }
   
   // Pre : Takes in a word (string) and max numbers of words allowed (int) allowed for
   //       combinations of anagrams. Unlimited words allowed if max equals 0. Throws
   //       an IllegalArgumentException if max is less than 0.
   // Post: Finds and prints all the combinations of words from the dictionary that are
   //       anagrams of the passed string.
   public void print(String s, int max) {
      if (max < 0) {
         throw new IllegalArgumentException();
      }
      LetterInventory anagram = new LetterInventory(s);
      Stack<String> result = new Stack<String>();
      explore(anagram, shorten(anagram), result, max);
   }
   
   // Pre : Takes in a given word to find anagrams (LetterInventory), list of viable
   //       anagrams (list), result of anagrams (stack) and max number of anagrams allwed (int).
   // Post: Finds all possible combinations of the given word to be printed as anagrams.
   private void explore(LetterInventory anagram, List reduced, Stack<String> result, int max) {
      if (anagram.isEmpty()) {
         System.out.println(result.toString());
      } else if (max == 0 || result.size() < max) {
         for (int i = 0; i < reduced.size(); i++) {
            String s = (String)reduced.get(i);
            if (anagram.subtract(dictionary.get(s)) != null) {
               result.push(s);
               explore(anagram.subtract(dictionary.get(s)), reduced, result, max);
               result.pop();
            }
         }
      }   
   }
   
   // Pre : Takes in a given word to find anagrams (LetterInventory).
   // Post: Prunes the dictionary for all viable anagram words and returns them as a list.
   private List<String> shorten(LetterInventory anagram) {
      List<String> reduced = new ArrayList<String>();
      for (String key : words) {
         if (anagram.subtract(dictionary.get(key)) != null) {
            reduced.add(key);
         }
      }
      return reduced;
   }
}