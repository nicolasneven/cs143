// Nicolas Neven
// 12/5/17
// CSE 142 BN
// TA: Alex Edward Smintina
// Assignment #9
//
// This progam extends the Bird class and returns paramaters that define the behavior
// of the Vulture critter

import java.awt.*;
public class Vulture extends Bird {

   private boolean fight;
   
   // Vulture constructor that sets boolean fight equal to true
   public Vulture() {
      fight = true;
   }
   
   // Returns a boolean true if the vulture had just gotten into a fight or just spawned,
   // and false if the vulture has not gotten into a fight after eating
   public boolean eat() {
		if (fight) {
         fight = false;
         return true;
      } else {
         return false;
      }
	}
   
   // Assigns the fight boolean back to true and calls the fight method from the bird superclass
   // that returns the next fight move
	public Attack fight(String opponent) {
      fight = true;
      return super.fight(opponent);
	}

   // Returns the color of the vulture which will display BLACK during the critter game
	public Color getColor() {
		return Color.BLACK;
	}
}