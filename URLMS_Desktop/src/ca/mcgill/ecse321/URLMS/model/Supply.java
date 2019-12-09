/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.URLMS.model;

// line 66 "../../../../../URLMS.ump"
public class Supply extends Item
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Supply Attributes
  private boolean isAvailable;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Supply(String aName, double aPrice, int aQuantity, boolean aIsAvailable)
  {
    super(aName, aPrice, aQuantity);
    isAvailable = aIsAvailable;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setIsAvailable(boolean aIsAvailable)
  {
    boolean wasSet = false;
    isAvailable = aIsAvailable;
    wasSet = true;
    return wasSet;
  }

  public boolean getIsAvailable()
  {
    return isAvailable;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "isAvailable" + ":" + getIsAvailable()+ "]";
  }
}