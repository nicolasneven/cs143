// Nicolas Neven
// 12/5/17
// CSE 142 BN
// TA: Alex Edward Smintina
// Assignment #9
//
// This progam extends the Critter class and returns paramaters that define the behavior
// of the Hippo critter

import java.awt.*;
import java.util.*;
public class Hippo extends Critter {

   private int hunger;
   private Random rand;
   private Color color;
   private int direction;
   private int step;
   private Attack attack;

   // Takes in an int and assigns that to the hunger a hippo has, creates a new random object for
   // every hippo critter, and initializes step to -1, color to GRAY, and attack to SCRATCH
   public Hippo(int hunger) {
      step = -1;
      color = Color.GRAY;
      attack = Attack.SCRATCH;
      rand = new Random();
      this.hunger = hunger; 
   }
   
   // Returns a boolean true if hippo hunger is not at 0 and false if hippo hunger is at 0
   public boolean eat() {
		if (hunger > 0) {
         hunger--;
         if (hunger == 0) {
            color = Color.WHITE;
            attack = Attack.POUNCE;
         }
         return true;
      } else {
         return false;
      }
	}
   
   // Returns attack POUNCE or SCRATCH depending on hippo hunger
	public Attack fight(String opponent) {
      return attack;
	}
   
   // Returns color WHITE or GRAY and assigns next attack all depending on hippo hunger
	public Color getColor() {
      return color;
	}

   // Returns a direction that moves the hippo 5 steps in a one random direction, then randomly 
   // chooses the next direction to move 5 steps towards
	public Direction getMove() {
      step++;
      if (step % 5 == 0) {
         direction = rand.nextInt(4);
      } if (direction == 0) {
         return Direction.NORTH;
      } if (direction == 1) {
         return Direction.EAST;
      } if (direction == 2) {
         return Direction.SOUTH;
      } else {
         return Direction.WEST;
      }         
	}
   
   // Returns a string that represents the hunger of a hippo during the critter game
	public String toString() {
		return "" + hunger;
	}
}