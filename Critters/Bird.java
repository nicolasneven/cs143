// Nicolas Neven
// 12/5/17
// CSE 142 BN
// TA: Alex Edward Smintina
// Assignment #9
//
// This progam extends the Critter class and returns paramaters that define the behavior
// of the Bird critter

import java.awt.*;
public class Bird extends Critter {

   private int step;
   private int direction;
   private String symbol;
   
   // Bird constructor that step and direction to -1 and symbol to "^"
   public Bird() {
      step = -1;
      direction = -1;
      symbol = "^";
   }
   
   // Returns a boolean that returns false everytime the bird encounters food
   public boolean eat() {
		return false;
	}
   
   // Returns an attack that returns ROAR if opponent is "%" otherwise returns POUNCE
	public Attack fight(String opponent) {
      if (opponent.equals("%")) {
         return Attack.ROAR;
      } else {
         return Attack.POUNCE;
      }
	}
   
   // Returns color that makes the bird BLUE during the critter game
	public Color getColor() {
		return Color.BLUE;
	}

   // Returns a direction that makes the bird move in a square  of size 3 and 
   // assigns the next string the bird will be when moving that direction
	public Direction getMove() {
      step++;
      if (step % 3 == 0) {
         direction++;
      } if (direction % 4 == 0) {
         symbol = "^";
         return Direction.NORTH;
      } if (direction % 4 == 1) {
         symbol = ">";
         return Direction.EAST;
      } if (direction % 4 == 2) {
         symbol = "V";
         return Direction.SOUTH;
      } else {
         symbol = "<";
         return Direction.WEST;
      }
	}

   // Returns a string that was assigned in getMove() that is displayed during the critter game
	public String toString() {
      return symbol;
   }
}