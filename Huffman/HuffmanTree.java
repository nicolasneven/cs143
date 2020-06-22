// Nicolas Neven
// 3/8/18
// CSE 143 AO
// TA: Benjamin Macmillan
// Assignment #8
//
// Takes an input text file and compresses that file into a one composed only of ASCII
// character codes and frequencies. Allows the user to then decode the compressed file
// onto an output file.

import java.io.*;
import java.util.*;
public class HuffmanTree {

   private HuffmanNode root;
   
   // Pre : Takes an count (array) of frequencies where index is the integer value of the
   //       character and the value at each index is the number of occurences in the file.
   // Post: Arranges the characters so the ones with low frequencies end up at the bottom
   //       of the HuffmanTree while characters with high freqeuncies are near the top.
   public HuffmanTree(int[] count) {
      Queue<HuffmanNode> frequencies = new PriorityQueue<HuffmanNode>();
      for (int i = 0; i < count.length; i++) {
         if (count[i] > 0) {
            frequencies.add(new HuffmanNode(i, count[i]));
         }
      }
      frequencies.add(new HuffmanNode(count.length, 1));
      while (frequencies.size() > 1) {
         HuffmanNode left = frequencies.remove();
         HuffmanNode right = frequencies.remove();
         frequencies.add(new HuffmanNode(0, (left.frequency + right.frequency), left, right));
      }
      
      root = frequencies.remove();
   }   
   
   // Pre : Takes in a input file (Scanner). Assumes it is in proper format.
   // Post: Uses the input file to reconstruct a HuffmanTree and puts the characters with low
   //       frequencies at the bottom with high frequency characters near the top.
   public HuffmanTree(Scanner input) {
      while (input.hasNextLine()) {
         root = HuffmanTree(root, Integer.parseInt(input.nextLine()), input.nextLine());
      }
   }
   
   // Pre : Takes in a node (HuffmanNode), int (character), and code (String).
   // Post: Builds up a HuffmanTree of characters with low frequencies at the bottom with high
   //       frequencies near the top. Returns the placement of the character within the
   //       HuffmanTree.
   private HuffmanNode HuffmanTree(HuffmanNode node, int character, String code) {
      if ("".equals(code)) {
         node = new HuffmanNode(character, -1);
      } else {
         if (node == null) {
            node = new HuffmanNode();
         }
         if (code.startsWith("0")) {
            node.left = HuffmanTree(node.left, character, code.substring(1));
         } else {
            node.right = HuffmanTree(node.right, character, code.substring(1));
         }
      }
      return node;
   }
   
   // Pre : Takes in an output file (PrintStream).
   // Post: Writes onto a a given output file the characters and frequencies from a tree
   //       in standard format.
   public void write(PrintStream output) {
      write(root, "", output);
   }
   
   // Pre : Takes in a node (HuffmanNode), code (String), and output (PrintStream).
   // Post: Writes onto a given outputfile the correct characters and frequencies in standard
   //       format.
   private void write(HuffmanNode node, String code, PrintStream output) {
      if (node.isLeafNode()) {
         output.println(node.character);
         output.println(code);
      } else {
         write(node.left, code + "0", output);
         write(node.right, code + "1", output);
      }
   }  
   
   // Pre : Takes in an input (BitInputStream), output (PrintStream), and eof (int).
   // Post: Reads bits from input file and writes the corresponding characters to the output.
   //       Stops reading when the character is equal to the eof paramater. Assumes the input
   //       contains legal encoding of characters for the current tree's Huffman code.
   public void decode(BitInputStream input, PrintStream output, int eof) {
      HuffmanNode node = root;
      while (node.character != eof) {
         if (node.isLeafNode()) {
            output.write(node.character);
            node = root;
         } else {
            if (input.readBit() == 0) {
               node = node.left;
            } else {
               node = node.right;
            }
         }
      }     
   }
}