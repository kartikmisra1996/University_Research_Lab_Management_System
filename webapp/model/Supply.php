<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-341e397-3616 modeling language!*/

class Supply extends Item
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Supply Attributes
  private $isAvailable;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aPrice, $aQuantity, $aIsAvailable)
  {
    parent::__construct($aName, $aPrice, $aQuantity);
    $this->isAvailable = $aIsAvailable;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setIsAvailable($aIsAvailable)
  {
    $wasSet = false;
    $this->isAvailable = $aIsAvailable;
    $wasSet = true;
    return $wasSet;
  }

  public function getIsAvailable()
  {
    return $this->isAvailable;
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