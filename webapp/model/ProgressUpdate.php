<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-341e397-3616 modeling language!*/

class ProgressUpdate
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ProgressUpdate Attributes
  private $objective;
  private $actualWork;
  private $date;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aObjective, $aActualWork, $aDate)
  {
    $this->objective = $aObjective;
    $this->actualWork = $aActualWork;
    $this->date = $aDate;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setObjective($aObjective)
  {
    $wasSet = false;
    $this->objective = $aObjective;
    $wasSet = true;
    return $wasSet;
  }

  public function setActualWork($aActualWork)
  {
    $wasSet = false;
    $this->actualWork = $aActualWork;
    $wasSet = true;
    return $wasSet;
  }

  public function setDate($aDate)
  {
    $wasSet = false;
    $this->date = $aDate;
    $wasSet = true;
    return $wasSet;
  }

  public function getObjective()
  {
    return $this->objective;
  }

  public function getActualWork()
  {
    return $this->actualWork;
  }

  public function getDate()
  {
    return $this->date;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {}

}
?>