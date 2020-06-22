// Nicolas Neven
// 12/5/17
// CSE 142 BN
// TA: Alex Edward Smintina
// Assignment #9
// 
// This progam extends the Critter class and returns paramaters that define the behavior
// of the Husky critter

import java.awt.*;
import java.util.*;
public class Husky extends Critter {

   private int attack; 
   private Random rand;
   private int distance;
   private int direction;
   private int step;
   private String camoflauge;
   
   // Creates a random object for every husky, assigns a random int for direction and attack,
   // initiliazes step to -1, initliazes camoflauge to ""
   public Husky() {
      step = -1;
      camoflauge = "";
      rand = new Random();
      direction = rand.nextInt(2);
      attack = rand.nextInt(3);
   }
   
   // Returns boolean true that makes the husky eat every time it encounters food
   public boolean eat() {
      return true;
	}

   // Returns an attack that beats the known critters, else returns a random attack
   // Assigns the husky to look like its last fought opponent = camoflauge
	public Attack fight(String opponent) {
      camoflauge = opponent;
      if (opponent.equals("^") || opponent.equals(">") || opponent.equals("V") ||
          opponent.equals("<") || opponent.equals("0")) {
         return Attack.SCRATCH;
      } else if (opponent.equals("%") || opponent.equals("1") || opponent.equals("2") ||
                 opponent.equals("3") || opponent.equals("4") || opponent.equals("5") ||
                 opponent.equals("6") || opponent.equals("7") || opponent.equals("8") ||
                 opponent.equals("9")) {
         return Attack.ROAR;
      } else {
         if (attack == 0) {
            return Attack.ROAR;
         } else if (attack == 1) {
            return Attack.POUNCE;
         } else {
            return Attack.SCRATCH;
         }
      }
	}

   // Returns color MAGENTA on every even move and YELLOW on every odd move
	public Color getColor() {
      if (step % 2 == 0) {
         return Color.MAGENTA;
      } else {
         return Color.YELLOW;
      }
	}
   
   // Returns direction EAST/WEST depending on the random direction and keeps that direction
   // until it has moved across the whole width of the screen, then moves SOUTH/NORTH
   // depending on the random direction
	public Direction getMove() {
      step++;
      distance = getWidth();
      if (step % distance == 0) {
         if (direction == 0) {
            return Direction.SOUTH;
         } else {
            return Direction.NORTH;
         }
      } else {
         if (direction == 0) {
            return Direction.EAST;
         } else {
            return Direction.WEST;
         }
      }  
	}
   
   // Returns a string that makes the husky look like whoever it fought last and defeated
	public String toString() {
      return camoflauge;
	}
}