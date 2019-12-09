/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.URLMS.model;
import java.sql.Date;

// line 14 "../../../../../URLMS.ump"
public class ProgressUpdate
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ProgressUpdate Attributes
  private String objective;
  private String actualWork;
  private Date date;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ProgressUpdate(String aObjective, String aActualWork, Date aDate)
  {
    objective = aObjective;
    actualWork = aActualWork;
    date = aDate;
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public String getObjective()
  {
    return objective;
  }

  public String getActualWork()
  {
    return actualWork;
  }

  public Date getDate()
  {
    return date;
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