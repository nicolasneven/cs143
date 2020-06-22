// Nicolas Neven
// 11/14/17
// CSE 142 BN
// TA: Alex Edward Smintina
// Assignment #6
//
// Creates a Mad Libs using an input file, prompts user for input, and creates a new output file
// that has all placeholders filled in by the user. Views any file, input or output. 

import java.util.*;
import java.io.*;
public class MadLibs {
   public static void main(String[] args) throws FileNotFoundException {
      
      intro();
      String response = "";
      Scanner console = new Scanner(System.in);
      
      // Creates the UI prompt that allows the user to create, view, or quit from the Mad Libs game
      while (!response.equals("Q")) {
         System.out.print("(C)reate mad-lib, (V)iew mad-lib, (Q)uit? ");
         response = console.nextLine().toUpperCase();
         if (response.equals("C") || response.equals("V")) {
            File inputFile = new File("");
            inputFile = fileName("Input file name: ", console);
            while (!inputFile.exists()) {
               inputFile = fileName("File not found. Try again: ", console);
            }
            Scanner fileReader = new Scanner(inputFile);
            if (response.equals("C")) {
               File outputFile = new File("");
               outputFile = fileName("Output file name: ", console);
               create(console, fileReader, outputFile); 
            } else {
               view(fileReader);
            }
         }
      }
   }
   
   // Print the introduction message with instructions for the Mad Libs game
   public static void intro() {
      
      System.out.println("Welcome to the game of Mad Libs.");
      System.out.println("I will ask you to provide various words");
      System.out.println("and phrases to fill in a story.");
      System.out.println("The result will be written to an output file.");
      System.out.println();
   }
   
   // Takes a scanner console as paramater and creates / renames the file the user inputs
   public static File fileName(String prompt, Scanner console) {
      
      System.out.print(prompt);
      File data = new File(console.nextLine());
      return data;
   }
   
   // Creates the mad lib by taking in a scanner console for user word input, and rewrites
   // the input file onto a new output file.
   public static void create(Scanner console, Scanner fileReader, File outputFile)
         throws FileNotFoundException {
      
      System.out.println();
      PrintStream output = new PrintStream(outputFile);
      
      while (fileReader.hasNextLine()) {
         String line = fileReader.nextLine();
         Scanner token = new Scanner(line);
         
         while(token.hasNext()) {
            String word = token.next();
            
            if (word.startsWith("<") && word.endsWith(">")) {
               String placeHolder = word.replace("-", " ").substring(1, word.length()-1);
               String letter = placeHolder.toUpperCase().substring(0, 1);
               System.out.print("Please type");
               
               if (letter.equals("A") || letter.equals("E") || letter.equals("I")
                                      || letter.equals("O") || letter.equals("U")) {
                  
                  System.out.print(" an ");
               } else {
                  System.out.print(" a ");
               }
               System.out.print(placeHolder + ": ");
               word = console.nextLine();
            }
            output.print(word + " ");
         }
         output.println();
      }
      System.out.println("Your mad-lib has been created!");
      System.out.println();
   }
   
   // Views the file by taking in the output file and prints the messages onto the console
   public static void view(Scanner fileReader) throws FileNotFoundException {
      
      System.out.println();
      while (fileReader.hasNextLine()) {
         System.out.println(fileReader.nextLine());
      }
      System.out.println();
   }
}