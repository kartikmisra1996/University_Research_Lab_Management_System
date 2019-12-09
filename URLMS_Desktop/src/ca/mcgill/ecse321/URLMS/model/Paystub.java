/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.URLMS.model;
import java.sql.Date;

// line 38 "../../../../../URLMS.ump"
public class Paystub extends Transaction
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Paystub Attributes
  private double employeeID;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Paystub(Date aDate, String aDescription, double aAmount, double aEmployeeID)
  {
    super(aDate, aDescription, aAmount);
    employeeID = aEmployeeID;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setEmployeeID(double aEmployeeID)
  {
    boolean wasSet = false;
    employeeID = aEmployeeID;
    wasSet = true;
    return wasSet;
  }

  public double getEmployeeID()
  {
    return employeeID;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "employeeID" + ":" + getEmployeeID()+ "]";
  }
}