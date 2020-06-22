// Nicolas Neven
// 1/18/18
// CSE 143 AO
// TA: Benjamin Macmillan
// Assignment #2A
//
// This program models a vibrating guitar string of a given frequency that
// track of a ring buffer

import java.util.*;
public class GuitarString {

   private Queue<Double> ringBuffer;                // keeps track of ring buffer
   public static final double ENERGY_DECAY = 0.996; // enegry decay factor
   
   // Pre : Takes in a frequency paramater (double) and throwns an 
   // IllegalArgumentException if frequency <= 0 or ring buffer size < 2
   // Post: Constructs a guitar string of the given frequency
   public GuitarString(double frequency) {
      ringBuffer = new LinkedList<Double>();
      int N = (int)Math.round(StdAudio.SAMPLE_RATE / frequency);
      if (frequency <= 0 || N < 2) {
         throw new IllegalArgumentException();
      }
      for (int i = 0; i < N; i++) {
         ringBuffer.add(0.0);
      }
   }
   
   // Pre : Takes in an inital array (doubles) as a paramater and throws an
   // IllegalArgumentException if array has less than 2 elements
   // Post: Constructs a guitar string and initiliazes the contents of the ring
   // buffer to the values in the array
   public GuitarString(double[] init) {
      ringBuffer = new LinkedList<Double>();
      if (init.length < 2) {
         throw new IllegalArgumentException();
      }
      for (int i = 0; i < init.length; i++) {
         ringBuffer.add(init[i]);
      }
   }
   
   // Post: Replaces the N elements in the ring buffer with N random values
   public void pluck() {
      for (int i = 0; i < ringBuffer.size(); i++) {
         ringBuffer.remove();
         ringBuffer.add(Math.random() - 0.5);
      }
   }
   
   // Post: Applies the Karpus-Strong algorithim once
   public void tic() {
      ringBuffer.add((0.5 * (ringBuffer.remove() + ringBuffer.peek())) * ENERGY_DECAY);
   }
   
   // Post: Returns the value at the front of the ring buffer (current sample)
   public double sample() {
      return ringBuffer.peek();
   }
}