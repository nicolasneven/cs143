// Nicolas Neven
// 3/1/18
// CSE 143 AO
// TA: Benjamin Macmillan
// Assignment #7
//
// Allows the yes/no guessing game to store question/answers which can correspond to other
// data values.

public class QuestionNode {
   public String data;
   public QuestionNode no;
   public QuestionNode yes;
   
   // Pre : Takes in data (String).
   // Post: Constructs a new QuestionNode that does not correspond to any other data values.
   public QuestionNode(String data) {
      this(data, null, null);
   }
   
   // Pre : Takes in data (String), yes (QuestionNode), and no (QuestionNode).
   // Post: Constructs a new QuestionNode that has a corresponding yes and no data values.
   public QuestionNode(String data, QuestionNode yes, QuestionNode no) {
      this.data = data;
      this.yes = yes;
      this.no = no;
   }
   
   // Post: Returns a boolean yes if the current is a leaf node (has no corresponding
   //       yes and no data values) and false otherwise.
   public boolean isLeafNode() {
      return (this.no == null) && (this.yes == null);
   }
}