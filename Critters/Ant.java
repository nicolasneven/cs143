// Nicolas Neven
// 12/5/17
// CSE 142 BN
// TA: Alex Edward Smintina
// Assignment #9
//
// This progam extends the Critter class and returns paramaters that define the behavior
// of the Ant critter

import java.awt.*;
public class Ant extends Critter {
   
   private boolean walkSouth;
   private int step;

   // Takes in boolean as a paramater that determines if the ant will move South or North
   // and initializes step to -1
   public Ant(boolean walkSouth) {
      step = -1;
      this.walkSouth = walkSouth; 
   }
   
   // Returns boolean true that makes the ant eat every time it encounters food
   public boolean eat() {
		return true;
	}
   
   // Returns an attack that makes the ant SCRATCH every time it gets into a fight
	public Attack fight(String opponent) {
		return Attack.SCRATCH;
	}
   
   // Returns a color that makes the ant RED during the critter game
	public Color getColor() {
		return Color.RED;
	}

   // Returns a dircetion that makes the ant move EAST after it has moved NORTH/SOUTH
   // depending on the walkSouth boolean
	public Direction getMove() {
      step++;
      if (step % 2 == 0) {
         if (walkSouth) {
            return Direction.SOUTH;
         } else {
            return Direction.NORTH;
         }
      } else {
		   return Direction.EAST;
      }
	}
   
   // Returns a string that makes the ant look like "%" during the critter game
	public String toString() {
		return "%";
	}
}