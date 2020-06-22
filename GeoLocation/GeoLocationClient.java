// Nicolas Neven
// 11/28/17
// CSE 142 BN
// TA: Alex Edward Smintina
// Assignment #8B
//
// Constructs and manipulates 3 GeoLocation objects of Breaking Bad geographic information
// and prints their location and distances between them

public class GeoLocationClient {
   public static void main(String[] args) {

   GeoLocation stash = new GeoLocation(34.988889, -106.614444);
   GeoLocation ABQ = new GeoLocation(34.989978, -106.614357);
   GeoLocation FBI = new GeoLocation(35.131281, -106.61263);
   
   System.out.println("the stash is at " + stash.toString());
   System.out.println("ABQ studio is at " + ABQ.toString());
   System.out.println("FBI building is at " + FBI.toString());
   System.out.println("distance in miles between:");
   System.out.println("    stash/studio = " + stash.distanceFrom(ABQ));
   System.out.println("    stash/fbi    = " + stash.distanceFrom(FBI));
   
   }
}