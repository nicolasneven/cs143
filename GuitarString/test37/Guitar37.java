// Nicolas Neven
// 1/18/18
// CSE 143 AO
// TA: Benjamin Macmillan
// Assignment #2B
//
// Keeps track of a musical instrument with 37 guitar strings that allows
// a user to play specific notes

import java.util.*;
public class Guitar37 implements Guitar {

   private GuitarString[] strings;                // list of guitar strings
   private int time;                              // time elapsed per tic
   
   public static final String KEYBOARD =
        "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout
   
   // Post: Constructs an array of guitar strings with of different frequencies
   public Guitar37() {
      strings = new GuitarString[KEYBOARD.length()];
      for (int i = 0; i < KEYBOARD.length(); i++) {
         strings[i] = new GuitarString(440.0 * Math.pow(2, ((i - 24.0) / 12.0)));
      }
   }
   
   // Pre : Takes in a pitch paramater (int)
   // Post: Plays a specific note based on the given pitch
   public void playNote(int pitch) {
      if (pitch >= -24 && pitch <= 12) {
         strings[pitch + 24].pluck();
      }
   }
   
   // Pre : Takes in a key paramater (char)
   // Post: Returns a boolean depending on if the key pressed is valid or not
   public boolean hasString(char key) {
      return KEYBOARD.contains(String.valueOf(key));
   }
   
   // Pre : Takes in a key paramater (char) and if key is not valid then throws
   // an IllegalArgumentException
   // Post: Plays a specific note based on the given key
   public void pluck(char key) {
      if (!hasString(key)) {
         throw new IllegalArgumentException();
      }
      strings[KEYBOARD.indexOf(key)].pluck();
   }
   
   // Post: Returns a sum of the current samples
   public double sample() {
      double total = 0.0;
      for (GuitarString note : strings) {
         total += note.sample();
      }
      return total;
   }
   
   // Post: Tics every note and advances time forward by one tic
   public void tic() {
      for (GuitarString note : strings) {
         note.tic();
      }
      time++;
   }
   
   // Post: Returns the current time (number of times tic has been called)
   public int time() {
      return time;
   }
}