// Nicolas Neven
// 1/25/18
// CSE 143 AO
// TA: Benjamin Macmillan
// Assignment #3
//
// Allows a client to manage a game of assassin. Each player (who is assigned a killer) gets
// placed into a kill ring. Once the player dies they are removed and put into the graveyard.

import java.util.*;
public class AssassinManager {
   
   private AssassinNode alive; // kill ring list of those currently alive
   private AssassinNode dead;  // graveyard list of those who have died
   
   // Pre : Takes in a list of names (string) and throws and IllegalArgumentException
   // if list is empty
   // Post: Adds the names from the list into the kill ring in the same order in which
   // they appear in the list
   public AssassinManager(List<String> names) {
      alive = null;
      if (names.isEmpty()) {
         throw new IllegalArgumentException();
      }
      for (int i = names.size() - 1; i >= 0; i--) {
         alive = new AssassinNode(names.get(i), alive);
      }
   }
   
   // Post: Prints the names of the people in the kill ring in the order of who is stalking who.
   // If there is only one person in the ring, it prints that they are stalking themselves 
   public void printKillRing() {
      AssassinNode current = alive;
      while (current.next != null) {
         System.out.println("    " + current.name + " is stalking " + current.next.name);
         current = current.next;
      }
      System.out.println("    " + current.name + " is stalking " + alive.name);
   }
   
   // Post:  Prints the names of the people in the graveyears in order of who killed who.
   // Prints the names in reverse order (most recently killed is printed first)
   public void printGraveyard() {
      AssassinNode current = dead;
      while (current != null) {
         System.out.println("    " + current.name + " was killed by " + current.killer);
         current = current.next;
      }
   }
   
   // Pre : Takes in a name (string)
   // Post: Returns a boolean true if the given name is in the current kill ring
   // and returns false if not
   public boolean killRingContains(String name) {
      return contains(alive, name);
   }
   
   // Pre: Takes in name (string)
   // Post: Returns a boolean true if the given name is in the current graveyard
   // and returns false if not
   public boolean graveyardContains(String name) {
      return contains(dead, name);
   }
   
   // Post: Returns a boolena true if the game is over (kill ring has one person in it)
   // and returns false if not
   public boolean gameOver() {
      return alive.next == null;
   }
   
   // Post: Returns the name of the winner of the game (string) and null if the game is not over
   public String winner() {
      if (gameOver() == true) {
         return alive.name;
      }
      return null;
   }
   
   // Pre: Takes in a name (string). Throws an IllegalArgumentExcpetion if the given name is
   // not part of the current kill ring. Throws an IllegalStateException if the game is over
   // Post: Records the killing of the person with the given name, tranferring the person from
   // the kill ring to the graveyard. Ignores case when comparing names and ignores case when
   // comparing names
   public void kill(String name) {
      AssassinNode previous = dead;
      AssassinNode current = alive;
      if (!killRingContains(name)) {
         throw new IllegalArgumentException();
      } else if (gameOver()) {
         throw new IllegalStateException();
      }
      if (current.name.equalsIgnoreCase(name)) {
         while (current.next != null) {
            current = current.next;
         }
         dead = alive;
         alive.killer = current.name;
         alive = alive.next;
      } else {
         while (!current.next.name.equalsIgnoreCase(name)) {
            current = current.next;
         }
         dead = current.next;
         dead.killer = current.name;
         current.next = current.next.next;
      }
      dead.next = previous;
   }
   
   // Pre: Takes in a reference that points to the front of an AssasinNode and name (string)
   // Post: Checks if the given name is in the given list ignoring case when comparing names
   private boolean contains(AssassinNode list, String name) {
      AssassinNode current = list;
      while (current != null) {
         if (current.name.equalsIgnoreCase(name)) {
            return true;
         }
         current = current.next;
      }
      return false;
   }
}