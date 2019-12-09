/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

package ca.mcgill.ecse321.URLMS.model;
import java.sql.Date;

// line 13 "../../../../../URLMS.ump"
public class ProgressUpdate
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ProgressUpdate Attributes
  private Date date;
  private String objective;
  private String actualWork;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ProgressUpdate(Date aDate, String aObjective, String aActualWork)
  {
    date = aDate;
    objective = aObjective;
    actualWork = aActualWork;
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

  public boolean setObjective(String aObjective)
  {
    boolean wasSet = false;
    objective = aObjective;
    wasSet = true;
    return wasSet;
  }

  public boolean setActualWork(String aActualWork)
  {
    boolean wasSet = false;
    actualWork = aActualWork;
    wasSet = true;
    return wasSet;
  }

  public Date getDate()
  {
    return date;
  }

  public String getObjective()
  {
    return objective;
  }

  public String getActualWork()
  {
    return actualWork;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "objective" + ":" + getObjective()+ "," +
            "actualWork" + ":" + getActualWork()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null");
  }
}