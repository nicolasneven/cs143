// Nicolas Neven
// 11/21/17
// CSE 142 BN
// TA: Alex Edward Smintina
// Assignment #7
//
// This progam prompts the user for an input file and produces an output file with:
// DNA name, nucleotide sequence, nucleotide counts, total mass, codon list, and protein prediction

import java.util.*;
import java.io.*;
public class DNA {

   public static int MINCODONS = 5;
   public static int PERCENTAGE = 30;
   public static int UN = 4;
   public static int NPC = 3;
   
   public static void main(String[] args) throws FileNotFoundException {
   
      Scanner console = new Scanner(System.in);
      Scanner input = new Scanner(intro(console));
      File outputFile = new File(console.nextLine());
      PrintStream output = new PrintStream(outputFile);
      while (input.hasNextLine()) {
         String regionName = input.nextLine();
         String sequence = input.nextLine().toUpperCase();
         int[] counts = counts(sequence);
         int[] junkCounts = junkCounts(sequence, counts);
         double[] masses = masses(junkCounts);
         double massTotal = massTotal(masses);
         double[] massPercentage = massPercentage(counts, massTotal);
         String[] codons = codons(sequence);
         String protein = protein(massPercentage, codons);
         output(output, regionName, sequence, counts, massPercentage, massTotal, codons, protein);
      }
   }
   
   // Prints introduction and prompts user for input file
   public static File intro(Scanner console) {
   
      System.out.println("This program reports information about DNA");
      System.out.println("nucleotide sequences that may encode proteins.");
      System.out.print("Input file name? ");
      File inputFile = new File(console.nextLine());
      System.out.print("Output file name? ");
      return inputFile;
   }
   
   // Takes in the nucleotide sequence and creates an array that has counts for each nucleotide
   public static int[] counts(String sequence) {
   
      int[] counts = new int[UN];
      for (int i = 0; i < sequence.length(); i++) {
          char c = sequence.charAt(i);
          if (c == 'A') {
            counts[0]++;
          } else if (c == 'C') {
            counts[1]++;
          } else if (c == 'G') {
            counts[2]++;
          } else if (c == 'T') {
            counts[3]++;
          }
      }
      return counts;
   }
   
   // Takes in the sequence and counts array to returns a new array with "-" accounted for
   public static int[] junkCounts(String sequence, int[] counts) {
      
      int[] junkCounts = new int[UN + 1];
      for (int i = 0; i < counts.length; i++) {
         junkCounts[i] = counts[i];
      }
      for (int i = 0; i < sequence.length(); i++) {
         char c = sequence.charAt(i);
         if (c == '-') {
            junkCounts[UN]++;
         }
      }
      return junkCounts;
   }
   
   // Takes in the array of nucleotide and "-" counts and returns a new array with total mass value
   // of each individual nucleotide and junk
   public static double[] masses(int[] junkCounts) {
   
      double[] masses = new double[UN + 1];
      double[] massConstant = {135.128, 111.103, 151.128, 125.107, 100.00};
      for (int i = 0; i < junkCounts.length; i++) {
         masses[i] += junkCounts[i] * massConstant[i];  
      }
      return masses;
   }
   
   // Takes in the array of individual mass totals and return the total mass of the entire sequence
   public static double massTotal(double[] masses) {
      
      double massTotal = 0;
      for (int i = 0; i < masses.length; i++) {
         massTotal += masses[i];
      }
      massTotal = Math.round(massTotal * 10.0) / 10.0;
      return massTotal;
   }
   
   // Takes in the nucleotide counts array and total mass to return an array of mass percentages 
   public static double[] massPercentage(int[] counts, double massTotal) {
      
      double[] massPercentage = new double[UN];
      double[] massConstant = {135.128, 111.103, 151.128, 125.107, 100.00};
      for (int i = 0; i < counts.length; i++) {
         massPercentage[i] = counts[i] * massConstant[i] / massTotal * 100;
         massPercentage[i] = Math.round(massPercentage[i] * 10.0) / 10.0;
      }
      return massPercentage;
   }
   
   // Takes in the nucleotide sequence and returns a string array of nucleotides per codon
   public static String[] codons(String sequence) {
      
      sequence = sequence.replace("-", "");
      String[] codons = new String[sequence.length() / NPC];
      for (int i = 0; i < sequence.length() / NPC; i++) {
         codons[i] = sequence.substring(i * NPC, (i + 1) * NPC);
      }
      return codons;
   }
   
   // Takes in the mass percentage and codon arrays to return the protein prediction
   public static String protein(double[] massPercentage, String[] codons) {
      int stop = codons.length - 1;
      String protein = "NO";
      if (codons[stop].equals("TAA") || codons[stop].equals("TAG") || codons[stop].equals("TGA")) {
      
         if (codons[0].equals("ATG") && codons.length >= MINCODONS && 
             massPercentage[1] + massPercentage[2] >= PERCENTAGE) {
             
            protein = "YES";
         }
      }
      return protein;
   }
   
   // Takes in printstream and every paramater necessary for the outputfile returned/created in main
   // and prints it onto an output file
   public static void output(PrintStream output, String regionName, String sequence,
                             int[] counts, double[] massPercentage, double massTotal,
                             String[] codons, String protein) {
      
      output.println("Region Name: " + regionName);
      output.println("Nucleotides: " + sequence);
      output.println("Nuc. Counts: " + Arrays.toString(counts));
      output.println("Total Mass%: " + Arrays.toString(massPercentage) + " of " + massTotal);
      output.println("Codons List: " + Arrays.toString(codons));
      output.println("Is Protein?: " + protein);
      output.println();
   }
}