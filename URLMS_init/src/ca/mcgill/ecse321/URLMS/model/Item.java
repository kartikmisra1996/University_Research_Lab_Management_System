/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

package ca.mcgill.ecse321.URLMS.model;

// line 20 "../../../../../URLMS.ump"
public class Item
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Item Attributes
  private String name;
  private double quantity;
  private double basePrice;
  private String category;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Item(String aName, double aQuantity, double aBasePrice, String aCategory)
  {
    name = aName;
    quantity = aQuantity;
    basePrice = aBasePrice;
    category = aCategory;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setQuantity(double aQuantity)
  {
    boolean wasSet = false;
    quantity = aQuantity;
    wasSet = true;
    return wasSet;
  }

  public boolean setBasePrice(double aBasePrice)
  {
    boolean wasSet = false;
    basePrice = aBasePrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setCategory(String aCategory)
  {
    boolean wasSet = false;
    category = aCategory;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public double getQuantity()
  {
    return quantity;
  }

  public double getBasePrice()
  {
    return basePrice;
  }

  public String getCategory()
  {
    return category;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "quantity" + ":" + getQuantity()+ "," +
            "basePrice" + ":" + getBasePrice()+ "," +
            "category" + ":" + getCategory()+ "]";
  }
}