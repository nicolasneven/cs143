// Sample subclass of Super:
// 
public class Sub extends Super {
   // this m1 overrides the Super class's m1.
   public void m1(int x) {
      // but what if we want to actually call Super's m1()
      // in the subclass's m1()?
      // call super.m1() to call m1() in the superclass (Super.java)
      super.m1(x);
      System.out.println("sub 1");
   }
}
