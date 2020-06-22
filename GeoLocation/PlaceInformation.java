// Nicolas Neven
// 11/28/17
// CSE 142 BN
// TA: Alex Edward Smintina
// Assignment #8C
//
// Stores information about a place of interest on Earth for PlaceInformationClient.java specified
// by name, address, tag, latitude, and longitude

public class PlaceInformation {

   private String name;
   private String address;
   private String tag;
   private GeoLocation GL;

   // Constructs a place information object with given: name, address, tag, latitude, and longitude
   public PlaceInformation(String name, String address, String tag,
                           double latitude, double longitude) {
                          
      GL = new GeoLocation(latitude, longitude);
   }
   
   // Returns name of this PlaceInformation
   public String getName() {
      return name;
   }
   
   // Returns address of this PlaceInformation
   public String getAddress() {
      return address;
   }
   
   // Returns tag of this PlaceInformation
   public String getTag() {
      return tag;
   }
   
   // Returns a string of name and GeoLocation details
   public String toString() {
      return name + " (" + GL.toString() + ")"; 
   }
   
   // Returns a GeoLocation of this PlaceInformation
   public GeoLocation getLocation() {
      return GL;
   }
   
   // Returns a (double) distance of this Placeinformation with given spot
   public double distanceFrom(GeoLocation spot) {
      return GL.distanceFrom(spot);
   }
}