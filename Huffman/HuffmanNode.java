// Nicolas Neven
// 3/8/18
// CSE 143 AO
// TA: Benjamin Macmillan
// Assignment #8
//
// Stores the ASCII values of characters and their frequencies from a text file using
// Huffman compression techniques.

public class HuffmanNode implements Comparable<HuffmanNode> {
   public int character;
   public int frequency;
   public HuffmanNode left;
   public HuffmanNode right;
   
   // Post: Constrcuts a HuffmanNode that stores a 0 character value (null) with -1 frequency.
   //       Corresponding to no other data values.
   public HuffmanNode() {
      this(0, -1);
   }
   
   // Pre : Takes in an int (character) and int (frequency).
   // Post: Constructs a HuffmanNode with given character and freqeuncy, corresponding to no
   //       data values.
   public HuffmanNode(int character, int frequency) {
      this(character, frequency, null, null);
   }
   
   // Pre : Takes in an int (character), int (frequency), left (HuffmanNode),
   //       and right (HuffmanNode).
   // Post: Constructs a HuffmanNode with given character and frequency with corresponding
   //       data values of other character/frequency pairs.
   public HuffmanNode(int character, int frequency, HuffmanNode left, HuffmanNode right) {
      this.character = character;
      this.frequency = frequency;
      this.left = left;
      this.right = right;
   }
   
   // Post: Returns a boolean true if the current HuffmanNode does not have corresponding
   //       data values of charaacter/frequency pairs and false otherwise.
   public boolean isLeafNode() {
      return (this.left == null) && (this.right == null);
   }
   
   // Pre : Takes in an other (HuffmanNode).
   // Post: Returns an int that determines if the current frequency is greater, less than,
   //       or equal to the passed in frequency (other).
   public int compareTo(HuffmanNode other) {
      return this.frequency - other.frequency;
   }
}