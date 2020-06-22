// Nicolas Neven
// 3/1/18
// CSE 143 AO
// TA: Benjamin Macmillan
// Assignment #7
//
// Allows the user to play a yes/no guessing game, read in an existing game layout
// from a text file, or write the newly created game layout into a text file.

import java.io.*;
import java.util.*;
public class QuestionTree {

   private Scanner console;          // allows for user input
   private QuestionNode overallRoot; // first question/answer in the game layout
   
   // Post: Creates a new yes/no guessing game layout with "computer" as the first
   //       data value in the layout.
   public QuestionTree() {
      console = new Scanner(System.in);
      overallRoot = new QuestionNode("computer");   
   }
   
   // Pre : Takes in a input (Scanner).
   // Post: Replaces the original game layout by reading in an existing text file to create
   //       a new layout. Assumes the file is legal and in standard format.
   public void read(Scanner input) {
      overallRoot = read(overallRoot, input);
   }
   
   // Pre : Takes in a output (PrintStream).
   // Post: Takes the current yes/no guessing game layout being played and writes it to a text
   //       file in standard format.
   public void write(PrintStream output) {
      write(overallRoot, output);
   }
   
   // Post: Asks the user a series of yes/no questions until the computer either guess their
   //       object correctly or until it fails, in which case it will expand the game layout to
   //       incorporate the users new object and the question that distinguishes their object
   //       from the one that the computer guessed.
   public void askQuestions() {
      overallRoot = askQuestions(overallRoot);
   }
   
   // Pre : Takes in a prompt (String).
   // Post: Asks the user a question, forcing an answer of "y" or "n". Returns true if the
   //       answer was yes, returns false otherwise.
   public boolean yesTo(String prompt) {
      System.out.print(prompt + " (y/n)? ");
      String response = console.nextLine().trim().toLowerCase();
      while (!response.equals("y") && !response.equals("n")) {
         System.out.println("Please answer y or n.");
         System.out.print(prompt + " (y/n)? ");
         response = console.nextLine().trim().toLowerCase();
      }
      return response.equals("y");
   }
   
   // Pre : Takes in a node (QuestionNode) and input (Scanner).
   // Post: Replaces the current game layout with one from a text file. Assumes the file
   //       is legal and in standard format. Returns the proper node to to update game layout.
   private QuestionNode read(QuestionNode node, Scanner input) {
      String temp = input.nextLine();
      node = new QuestionNode(input.nextLine());
      if (temp.equals("Q:")) {
         node.yes = read(node.yes, input);
         node.no = read(node.no, input);
      }
      return node;
   }
   
   // Pre : Takes in a node (QuestionNode) and output (PrintStream).
   // Post: Stores the current game layout to an output text file in standard format.
   private void write(QuestionNode node, PrintStream output) {
      if (node.isLeafNode()) {
         output.println("A:");
         output.println(node.data);
      } else {
         output.println("Q:");
         output.println(node.data);
         write(node.yes, output);
         write(node.no, output);
      }
   }
   
   // Pre : Takes in a node (QuestionNode).
   // Post: Asks the user a series of yes/no questions until the computer either guesses
   //       their object correctly or until it fails in which case it will expand the game layout
   //       to incorporate the users new object and the question that distinguishes their object
   //       from the one that the computer guessed. Returns the proper node to update game layout.
   private QuestionNode askQuestions(QuestionNode node) {
      if (node.isLeafNode()) {
         if (yesTo("Would your object happen to be " + node.data + "?")) {
            System.out.println("Great, I got it right!");
         } else {
            System.out.print("What is the name of your object? ");
            QuestionNode answerNode = new QuestionNode(console.nextLine());
            System.out.println("Please give me a yes/no question that");
            System.out.println("distinguishes between your object");
            System.out.print("and mine--> ");
            String question = console.nextLine();
            if (yesTo("And what is the answer for your object?")) {
               node = new QuestionNode(question, answerNode, node);
            } else {
               node = new QuestionNode(question, node, answerNode);
            }
         }
      } else {
         if (yesTo(node.data)) {
            node.yes = askQuestions(node.yes);
         } else {
            node.no = askQuestions(node.no);
         }
      }
      return node;
   }  
}