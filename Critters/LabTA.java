import java.awt.*;
import java.util.*;
public class LabTA extends Critter {

   private boolean isWhite;
   private Random rand;
    
   public LabTA() {
      rand = new Random();
      isWhite = false;      // this line is not necessary, boolean default value is false
   }
    
   public Direction getMove() {
      isWhite = !isWhite;
      boolean goEast = rand.nextBoolean();
      if (goEast) {
         return Direction.EAST;
      } else {
         return Direction.WEST;
      }
   } 
    
   public Color getColor() {
      if (isWhite) {
         return Color.WHITE;
      } else {
         return Color.MAGENTA;
      }
   
   } 
   public boolean eat() {
		return true;
	}
   public Attack fight(String opponent) {
		return Attack.POUNCE;
	}

   public String toString() {
      return ":-)";
   }
}