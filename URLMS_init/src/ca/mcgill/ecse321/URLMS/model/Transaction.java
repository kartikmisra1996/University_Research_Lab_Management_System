/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

package ca.mcgill.ecse321.URLMS.model;
import java.sql.Date;

// line 29 "../../../../../URLMS.ump"
public class Transaction
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Transaction Attributes
  private Date date;
  private String description;
  private double amount;

  //Transaction Associations
  private Employee salaryCost;
  private Item itemCost;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Transaction(Date aDate, String aDescription, double aAmount)
  {
    date = aDate;
    description = aDescription;
    amount = aAmount;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public boolean setAmount(double aAmount)
  {
    boolean wasSet = false;
    amount = aAmount;
    wasSet = true;
    return wasSet;
  }

  public Date getDate()
  {
    return date;
  }

  public String getDescription()
  {
    return description;
  }

  public double getAmount()
  {
    return amount;
  }

  public Employee getSalaryCost()
  {
    return salaryCost;
  }

  public boolean hasSalaryCost()
  {
    boolean has = salaryCost != null;
    return has;
  }

  public Item getItemCost()
  {
    return itemCost;
  }

  public boolean hasItemCost()
  {
    boolean has = itemCost != null;
    return has;
  }

  public boolean setSalaryCost(Employee aNewSalaryCost)
  {
    boolean wasSet = false;
    salaryCost = aNewSalaryCost;
    wasSet = true;
    return wasSet;
  }

  public boolean setItemCost(Item aNewItemCost)
  {
    boolean wasSet = false;
    itemCost = aNewItemCost;
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    salaryCost = null;
    itemCost = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "description" + ":" + getDescription()+ "," +
            "amount" + ":" + getAmount()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "salaryCost = "+(getSalaryCost()!=null?Integer.toHexString(System.identityHashCode(getSalaryCost())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "itemCost = "+(getItemCost()!=null?Integer.toHexString(System.identityHashCode(getItemCost())):"null");
  }
}