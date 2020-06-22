// Prints a text-based histogram of the midterm exam scores

import java.util.*;
import java.io.*;

public class MidtermScores {
   public static void main(String[] args) throws FileNotFoundException {
      Scanner input = new Scanner(new File("mid-scores.txt"));

      // make an int[] that can hold 101 things: counts of scores 0-100
      int[] scoreCount = new int[101];

      // Each index of the array holds an int. 
      // In this program, we store a count inside each index, where
      // the index is the score on the exam.
      // So, at index 87 in the array, we want to store a count
      // of how many people got an 87 on the midterm
      
      // pull all the ints out of the data file
      while (input.hasNextInt()) {
         int score = input.nextInt();
 
         // the score tells us where in the array to look for the
         // count we want to increment -- the score is the index
         scoreCount[score] = scoreCount[score] + 1;
      }

      // use a forloop to iterate over all the scores:      
      for (int i = 0; i < scoreCount.length; i++) {
         System.out.print("Score: " + i + " - count = ");
         // use the count to control how many stars we print
         for (int j = 0; j < scoreCount[i]; j++) {
            System.out.print("*");
         }
         System.out.println();
      }
   }
}