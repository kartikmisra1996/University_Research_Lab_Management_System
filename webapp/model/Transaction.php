<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-341e397-3616 modeling language!*/

class Transaction
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Transaction Attributes
  private $date;
  private $description;
  private $amount;

  //Transaction Associations
  private $salaryCost;
  private $itemCost;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aDate, $aDescription, $aAmount)
  {
    $this->date = $aDate;
    $this->description = $aDescription;
    $this->amount = $aAmount;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setDate($aDate)
  {
    $wasSet = false;
    $this->date = $aDate;
    $wasSet = true;
    return $wasSet;
  }

  public function setDescription($aDescription)
  {
    $wasSet = false;
    $this->description = $aDescription;
    $wasSet = true;
    return $wasSet;
  }

  public function setAmount($aAmount)
  {
    $wasSet = false;
    $this->amount = $aAmount;
    $wasSet = true;
    return $wasSet;
  }

  public function getDate()
  {
    return $this->date;
  }

  public function getDescription()
  {
    return $this->description;
  }

  public function getAmount()
  {
    return $this->amount;
  }

  public function getSalaryCost()
  {
    return $this->salaryCost;
  }

  public function hasSalaryCost()
  {
    $has = $this->salaryCost != null;
    return $has;
  }

  public function getItemCost()
  {
    return $this->itemCost;
  }

  public function hasItemCost()
  {
    $has = $this->itemCost != null;
    return $has;
  }

  public function setSalaryCost($aNewSalaryCost)
  {
    $wasSet = false;
    $this->salaryCost = $aNewSalaryCost;
    $wasSet = true;
    return $wasSet;
  }

  public function setItemCost($aNewItemCost)
  {
    $wasSet = false;
    $this->itemCost = $aNewItemCost;
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $this->salaryCost = null;
    $this->itemCost = null;
  }

}
?>