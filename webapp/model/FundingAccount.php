<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-341e397-3616 modeling language!*/

class FundingAccount
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //FundingAccount Attributes
  private $balance;

  //FundingAccount Associations
  private $transactions;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aBalance)
  {
    $this->balance = $aBalance;
    $this->transactions = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setBalance($aBalance)
  {
    $wasSet = false;
    $this->balance = $aBalance;
    $wasSet = true;
    return $wasSet;
  }

  public function getBalance()
  {
    return $this->balance;
  }

  public function getTransaction_index($index)
  {
    $aTransaction = $this->transactions[$index];
    return $aTransaction;
  }

  public function getTransactions()
  {
    $newTransactions = $this->transactions;
    return $newTransactions;
  }

  public function numberOfTransactions()
  {
    $number = count($this->transactions);
    return $number;
  }

  public function hasTransactions()
  {
    $has = $this->numberOfTransactions() > 0;
    return $has;
  }

  public function indexOfTransaction($aTransaction)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->transactions as $transaction)
    {
      if ($transaction->equals($aTransaction))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfTransactions()
  {
    return 0;
  }

  public function addTransaction($aTransaction)
  {
    $wasAdded = false;
    if ($this->indexOfTransaction($aTransaction) !== -1) { return false; }
    $this->transactions[] = $aTransaction;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeTransaction($aTransaction)
  {
    $wasRemoved = false;
    if ($this->indexOfTransaction($aTransaction) != -1)
    {
      unset($this->transactions[$this->indexOfTransaction($aTransaction)]);
      $this->transactions = array_values($this->transactions);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addTransactionAt($aTransaction, $index)
  {  
    $wasAdded = false;
    if($this->addTransaction($aTransaction))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfTransactions()) { $index = $this->numberOfTransactions() - 1; }
      array_splice($this->transactions, $this->indexOfTransaction($aTransaction), 1);
      array_splice($this->transactions, $index, 0, array($aTransaction));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveTransactionAt($aTransaction, $index)
  {
    $wasAdded = false;
    if($this->indexOfTransaction($aTransaction) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfTransactions()) { $index = $this->numberOfTransactions() - 1; }
      array_splice($this->transactions, $this->indexOfTransaction($aTransaction), 1);
      array_splice($this->transactions, $index, 0, array($aTransaction));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addTransactionAt($aTransaction, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $this->transactions = array();
  }

}
?>