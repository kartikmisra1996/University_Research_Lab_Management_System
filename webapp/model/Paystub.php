<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-341e397-3616 modeling language!*/

class Paystub extends Transaction
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Paystub Attributes
  private $employeeID;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aDate, $aDescription, $aAmount, $aEmployeeID)
  {
    parent::__construct($aDate, $aDescription, $aAmount);
    $this->employeeID = $aEmployeeID;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setEmployeeID($aEmployeeID)
  {
    $wasSet = false;
    $this->employeeID = $aEmployeeID;
    $wasSet = true;
    return $wasSet;
  }

  public function getEmployeeID()
  {
    return $this->employeeID;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    parent::delete();
  }

}
?>