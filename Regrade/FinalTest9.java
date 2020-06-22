// Program to test solutions to problem #9 on the cse143 final, winter 2018.
// Fill in your solution to removeSmaller, then compile and run the program

import java.util.*;

class LinkedIntList {
   public LinkedIntList removeSmaller() {
      LinkedIntList result = new LinkedIntList();
      if (front != null && front.next != null) {
         if (front.data <= front.next.data) {
            result.front = front;
            front = front.next;
            result.front.next = null;
         } else {
            result.front = front.next;
            front.next = front.next.next;
            result.front.next = null;
         }
         ListNode temp1 = front;
         ListNode temp2 = result.front;
         while (temp1 !=  null && temp1.next != null && temp1.next.next != null) {
            if (temp1.next.data <= temp1.next.next.data) {
               temp2.next = temp1.next;
               temp1.next = temp1.next.next;
               temp2 = temp2.next;
               temp1 = temp1.next;
               temp2.next = null;
            } else {
               temp2.next = temp1.next.next;
               temp2 = temp2.next;
               temp1 = temp1.next;
               temp1.next = temp1.next.next;
               temp2.next = null;
            }
         }
      }
      return result;
   }
   
   private ListNode front;  // first value in the list
   
   // this is the sample solution
   public LinkedIntList removeSmaller2() {
      LinkedIntList other = new LinkedIntList();
      if (front != null && front.next != null) {
         if (front.data <= front.next.data) {
            other.front = front;
            front = front.next;
         } else {
            other.front = front.next;
            front.next = front.next.next;
         }
         ListNode current1 = front;
         ListNode current2 = other.front;
         while (current1.next != null && current1.next.next != null) {
            if (current1.next.data <= current1.next.next.data) {
               current2.next = current1.next;
               current1.next = current1.next.next;
            } else {
               current2.next = current1.next.next;
               current1.next.next = current1.next.next.next;
            }
            current1 = current1.next;
            current2 = current2.next;
         }
         current2.next = null;
      }
      return other;
   }
   
   // post: constructs an empty list
   public LinkedIntList() {
      front = null;
   }
   
   public boolean equals(Object o) {
      LinkedIntList other;
      try {
         other = (LinkedIntList) o;
      } catch (Exception e) {
         return false;
      }
      if (other == null) {
         return false;
      }
      ListNode current1 = front;
      ListNode current2 = other.front;
      while (current1 != null && current2 != null) {
         if (current1.data != current2.data)
            return false;
         current1 = current1.next;
         current2 = current2.next;
      }
      return current1 == null && current2 == null;
   }
   
   // post: creates a comma-separated, bracketed version of the list
   public String toString() {
      if (front == null)
         return "[]";
      else {
         String result = "[" + front.data;
         ListNode current = front.next;
         while (current != null) {
            result += ", " + current.data;
            current = current.next;
         }
         result += "]";
         return result;
      }
   }
   
   // post: creates a comma-separated, bracketed version of the list
   public String toString(int number) {
      if (front == null)
         return "[]";
      else {
         String result = "[" + front.data;
         int count = 1;
         ListNode current = front.next;
         while (current != null && count < number) {
            result += ", " + current.data;
            current = current.next;
            count++;
         }
         if (current != null) {
            result += "...";
         }
         result += "]";
         return result;
      }
   }
   
   // post: appends the given value to the end of the list
   public void add(int value) {
      if (front == null)
         front = new ListNode(value);
      else {
         ListNode current = front;
         while (current.next != null)
            current = current.next;
         current.next = new ListNode(value);
      }
   }
}

public class FinalTest9 {
   private static int totalCount = 0;
   private static int totalFail = 0;
   private static Random r = new Random();
   
   public static void main(String[] args) {
      for (int i = 0; i <= 15; i++) {
         test(i);
      }
      
      if (totalFail > 0) {
         System.out.println("failed " + totalFail + " of " + totalCount +
         " tests");
      } else {
         System.out.println("passed all " + totalCount + " tests");
      }
   }
   
   public static void test(int len) {
      for (int i = 0; i < 5; i++) {
         totalCount++;  // increment test counter
         // list1/list2 for testing sample, list3/list4 for their code
         LinkedIntList list1 = new LinkedIntList();
         LinkedIntList list2 = null;
         LinkedIntList list3 = new LinkedIntList();
         LinkedIntList list4 = null;
         for (int j = 0; j < len; j++) {
            int n = r.nextInt(101) - 25;
            list1.add(n);
            list3.add(n);
         }
         boolean fail = false;
         System.out.println("initial list    = " + list1);
         list2 = list1.removeSmaller2();
         System.out.println("list after call = " + list1);
         System.out.println("expected result = " + list2);
         try {
            list4 = list3.removeSmaller();
         } catch (RuntimeException e) {
            if(e.getStackTrace().length > 0) {
               int line = e.getStackTrace()[0].getLineNumber();
               System.out.println("    threw " + e + " at line #" + line);
            }
            fail = true;
         }
         if (!fail) {
            if (list1.equals(list3)) {
               System.out.println("your list matches");
            } else {
               System.out.println("your list       = " +
               list3.toString(len));
               fail = true;
            }
            if (list2.equals(list4)) {
               System.out.println("your result matches");
            } else {
               if (list4 == null) {
                  System.out.println("your result     = null");
               } else {
                  System.out.println("your result     = " +
                  list4.toString(len));
               }
               fail = true;
            }
         }
         if (fail) {
            System.out.println("failed");
            totalFail++;
         } else {
            System.out.println("passed");
         }
         System.out.println();
      }
   }
}

class ListNode {
   public final int data;       // data stored in this node
   public ListNode next;  // link to next node in the list
   
   // post: constructs a node with data 0 and null link
   public ListNode() {
      this(0, null);
   }
   
   // post: constructs a node with given data and null link
   public ListNode(int data) {
      this(data, null);
   }
   
   // post: constructs a node with given data and given link
   public ListNode(int data, ListNode next) {
      this.data = data;
      this.next = next;
   }
}
