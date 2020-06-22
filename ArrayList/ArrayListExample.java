import java.util.*;

// Sample usage of java's ArrayList object: a list that uses an array to store it's elements.
// list.get(i) is like asking an array for an element at an index: array[i]
// list.set(i, value) is like setting an element at an index: array[i] = value
// list.size() returns the number of elements in the list. (array.length gives the capacity of the array)
// list.remove(index) takes a value out of the list, shifting other elements towards the start of the list
// list.add(index, value) inserts a value into the list, shifting other elements towards the end of the list
public class ArrayListExample {
   public static void main(String[] args) {
      String[] wordArray = new String[50];
      wordArray[0] = "hello";
      wordArray[0] = "world";
      
      System.out.println("word array = " + Arrays.toString(wordArray));
      
      // Sample ArrayList construction and population:
      ArrayList<String> words = new ArrayList<String>();
      words.add("hello");
      words.add("four");
      words.add("score");
      words.add("and");
      words.add("seven");
      words.add("years");
      words.add("ago");
      words.add("ABRAHAM LINCOLNNNNNNN");
      words.add("a");
      words.set(0, "world");
      System.out.println("words ArrayList = " + words);

      minToFront(words);
      System.out.println("words ArrayList = " + words);

      stutter(words);
      System.out.println("words ArrayList = " + words);
      
      System.out.println("Longest word in the list has length: " + maxLength(words));
   }
   
   // return the lelngth of the longest string in the list
   public static int maxLength(ArrayList<String> words) {
      int maxLength = 0;
      for (int i = 0; i < words.size(); i++) {
         maxLength = Math.max(maxLength, words.get(i).length());
      }
      return maxLength;
   }
   
   // bring the element that is shortest in length to the front of the list.
   public static void minToFront(ArrayList<String> words) {
      if (words.size() != 0) {
         int minLength = words.get(0).length();
         int minValueIndex = 0;
         for (int i = 1; i < words.size(); i++) {
            String word = words.get(i);
            if (word.length() < minLength) {
               minLength = word.length();
               minValueIndex = i;
            }
         }

         // The following line of code does the work of
         // these three commented lines:
         // String shortestWord = words.get(minValueIndex);
         // words.remove(minValueIndex);
         // words.add(0, shortestWord);
         words.add(0, words.remove(minValueIndex));
      }
   }

   // replace every value in the list with two copies of that  value
   public static void stutter(ArrayList<String> words) {
      for (int i = 0; i < words.size(); i += 2) {
         words.add(i, words.get(i));
      }
   }
}
