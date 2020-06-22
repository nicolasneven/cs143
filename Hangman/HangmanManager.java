// Nicolas Neven
// 2/1/18
// CSE 143 AO
// TA: Benjamin Macmillan
// Assignment #4
//
// Updates the state of an evil hangman game by keeping track of the current set of words
// available, guesses used, guesses left, and current pattern of the word being guessed.

import java.util.*;
public class HangmanManager {

   private Set<String> words;            // list of words in the current hangman round
   private SortedSet<Character> guesses; // list of guesses used
   private int guessesLeft;              // number of guesses left
   private String pattern;               // current state of word being guessed
   
   // Pre : Takes in a dictionary of words (collection), target word length (int),
   //       and maximum number of wrong guesses (int) the player is allowed to make.
   //       Throws an IllegalArgumentException if length is less than 1 or if max is less than 0.
   // Post: Creates a sorted list of words from the dictionary of given length, without any
   //       duplicates. Creates the base pattern with dashes of given length. Sets the number
   //       of guesses left in the game to max.
   public HangmanManager(Collection<String> dictionary, int length, int max) {
      if (length < 1 || max < 0) {
         throw new IllegalArgumentException();
      }
      pattern = "-";
      guessesLeft = max;
      words = new TreeSet<String>();
      guesses = new TreeSet<Character>();
      for (String word : dictionary) {
         if (word.length() == length) {
            words.add(word);
         }
      }
      for (int i = 0; i < length - 1; i++) {
         pattern += " -";
      }
   }
   
   // Post: Returns the list of sorted words being used in the current round.
   public Set<String> words() {
      return words;
   }
   
   // Post: Returns how many guesses the player has left in the game.
   public int guessesLeft() {
      return guessesLeft;
   }
   
   // Post: Returns the sorted list of letters that have been guessed by the user.
   public SortedSet<Character> guesses() {
      return guesses;
   }
   
   // Pre : Throws an IllegalArgumentException if the current list of words is empty.
   // Post: Returns the current pattern to be displayed in the game taking into account
   //       the guesses that have been made. Letters that have not yet been guessed are displayed
   //       as a dash with spaces seperating the letters. Contains no leading or trailing spaces.
   public String pattern() {
      if (words.isEmpty()) {
         throw new IllegalArgumentException();
      }
      return pattern;   
   }
   
   // Pre : Takes in a guess (char). Throws an IllegalStateException if the number of
   //       guesses left is not at least 1 of or is the list of words is empty. Throws an
   //       IllegalArgumentException if the list of words is nonempty and the character being
   //       guessed was guessed previously.
   // Post: Records the next guess made by the user and decides what set of words to use for the
   //       next round. Returns the number of occurences the guessed letter in the new pattern and
   //       updates the number of guesses left. Adds the guess to the list of previous guesses.
   //       Chooses the highest number of words in a list to be used for the next round. Assigns
   //       the pattern to correspond to the new highest number of words in a list.
   public int record(char guess) {
      if (guessesLeft < 1 || words.isEmpty()) {
         throw new IllegalStateException();
      }
      if (!words.isEmpty() && guesses.contains(guess)) {
         throw new IllegalArgumentException();
      }
      int max = 0;
      guesses.add(guess);
      Map<String, Set<String>> dictionary = map(guess);
      for (String key : dictionary.keySet()) {
         if (dictionary.get(key).size() > max) {
            max = dictionary.get(key).size();
            words = dictionary.get(key);
            pattern = key;
         }
      }
      return count(guess);
   }
   
   // Pre : Takes in a guess (char).
   // Post: Returns a new map of the current available different sorted lists of words.
   //       Updates the pattern to add any new correct guesses while keeping old correct ones.
   private Map<String, Set<String>> map(char guess) {
      Map<String, Set<String>> dictionary = new TreeMap<String, Set<String>>();
      for (String word : words) {
         String key = "";
         for (int i = 0; i < word.length(); i++) {
            if (i > 0) {
               key += " ";
            }
            if (word.charAt(i) == guess) {
               key += guess;
            } else if (pattern.charAt(i * 2) != '-') {
               key += pattern.charAt(i * 2);
            } else {
               key += "-";
            }
         }
         if (!dictionary.containsKey(key)) {
            dictionary.put(key, new TreeSet<String>());
         }
         dictionary.get(key).add(word);
      }
      return dictionary;
   }
   
   // Pre : Takes in a guess (char).
   // Post: Returns how many times that guess occurs in the new pattern and decrements
   //       the number of guesses left is the guess is wrong.
   private int count(char guess) {
      int count = 0;
      for (int i = 0; i < pattern.length(); i++) {
         if (pattern.charAt(i) == guess) {
            count++;
         }
      }
      if (count == 0) {
         guessesLeft--;
      }
      return count;
   }
} 