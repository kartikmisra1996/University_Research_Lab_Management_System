<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-341e397-3616 modeling language!*/

class LaboratoryManager
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //LaboratoryManager Associations
  private $employees;
  private $items;
  private $progressUpdates;
  private $transactions;
  private $fundingAccount;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct()
  {
    $this->employees = array();
    $this->items = array();
    $this->progressUpdates = array();
    $this->transactions = array();
    $this->fundingAccount = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getEmployee_index($index)
  {
    $aEmployee = $this->employees[$index];
    return $aEmployee;
  }

  public function getEmployees()
  {
    $newEmployees = $this->employees;
    return $newEmployees;
  }

  public function numberOfEmployees()
  {
    $number = count($this->employees);
    return $number;
  }

  public function hasEmployees()
  {
    $has = $this->numberOfEmployees() > 0;
    return $has;
  }

  public function indexOfEmployee($aEmployee)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->employees as $employee)
    {
      if ($employee->equals($aEmployee))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getItem_index($index)
  {
    $aItem = $this->items[$index];
    return $aItem;
  }

  public function getItems()
  {
    $newItems = $this->items;
    return $newItems;
  }

  public function numberOfItems()
  {
    $number = count($this->items);
    return $number;
  }

  public function hasItems()
  {
    $has = $this->numberOfItems() > 0;
    return $has;
  }

  public function indexOfItem($aItem)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->items as $item)
    {
      if ($item->equals($aItem))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getProgressUpdate_index($index)
  {
    $aProgressUpdate = $this->progressUpdates[$index];
    return $aProgressUpdate;
  }

  public function getProgressUpdates()
  {
    $newProgressUpdates = $this->progressUpdates;
    return $newProgressUpdates;
  }

  public function numberOfProgressUpdates()
  {
    $number = count($this->progressUpdates);
    return $number;
  }

  public function hasProgressUpdates()
  {
    $has = $this->numberOfProgressUpdates() > 0;
    return $has;
  }

  public function indexOfProgressUpdate($aProgressUpdate)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->progressUpdates as $progressUpdate)
    {
      if ($progressUpdate->equals($aProgressUpdate))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
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

  public function getFundingAccount_index($index)
  {
    $aFundingAccount = $this->fundingAccount[$index];
    return $aFundingAccount;
  }

  public function getFundingAccount()
  {
    $newFundingAccount = $this->fundingAccount;
    return $newFundingAccount;
  }

  public function numberOfFundingAccount()
  {
    $number = count($this->fundingAccount);
    return $number;
  }

  public function hasFundingAccount()
  {
    $has = $this->numberOfFundingAccount() > 0;
    return $has;
  }

  public function indexOfFundingAccount($aFundingAccount)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->fundingAccount as $fundingAccount)
    {
      if ($fundingAccount->equals($aFundingAccount))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfEmployees()
  {
    return 0;
  }

  public function addEmployee($aEmployee)
  {
    $wasAdded = false;
    if ($this->indexOfEmployee($aEmployee) !== -1) { return false; }
    $this->employees[] = $aEmployee;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeEmployee($aEmployee)
  {
    $wasRemoved = false;
    if ($this->indexOfEmployee($aEmployee) != -1)
    {
      unset($this->employees[$this->indexOfEmployee($aEmployee)]);
      $this->employees = array_values($this->employees);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addEmployeeAt($aEmployee, $index)
  {  
    $wasAdded = false;
    if($this->addEmployee($aEmployee))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfEmployees()) { $index = $this->numberOfEmployees() - 1; }
      array_splice($this->employees, $this->indexOfEmployee($aEmployee), 1);
      array_splice($this->employees, $index, 0, array($aEmployee));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveEmployeeAt($aEmployee, $index)
  {
    $wasAdded = false;
    if($this->indexOfEmployee($aEmployee) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfEmployees()) { $index = $this->numberOfEmployees() - 1; }
      array_splice($this->employees, $this->indexOfEmployee($aEmployee), 1);
      array_splice($this->employees, $index, 0, array($aEmployee));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addEmployeeAt($aEmployee, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfItems()
  {
    return 0;
  }

  public function addItem($aItem)
  {
    $wasAdded = false;
    if ($this->indexOfItem($aItem) !== -1) { return false; }
    $this->items[] = $aItem;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeItem($aItem)
  {
    $wasRemoved = false;
    if ($this->indexOfItem($aItem) != -1)
    {
      unset($this->items[$this->indexOfItem($aItem)]);
      $this->items = array_values($this->items);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addItemAt($aItem, $index)
  {  
    $wasAdded = false;
    if($this->addItem($aItem))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfItems()) { $index = $this->numberOfItems() - 1; }
      array_splice($this->items, $this->indexOfItem($aItem), 1);
      array_splice($this->items, $index, 0, array($aItem));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveItemAt($aItem, $index)
  {
    $wasAdded = false;
    if($this->indexOfItem($aItem) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfItems()) { $index = $this->numberOfItems() - 1; }
      array_splice($this->items, $this->indexOfItem($aItem), 1);
      array_splice($this->items, $index, 0, array($aItem));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addItemAt($aItem, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfProgressUpdates()
  {
    return 0;
  }

  public function addProgressUpdate($aProgressUpdate)
  {
    $wasAdded = false;
    if ($this->indexOfProgressUpdate($aProgressUpdate) !== -1) { return false; }
    $this->progressUpdates[] = $aProgressUpdate;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeProgressUpdate($aProgressUpdate)
  {
    $wasRemoved = false;
    if ($this->indexOfProgressUpdate($aProgressUpdate) != -1)
    {
      unset($this->progressUpdates[$this->indexOfProgressUpdate($aProgressUpdate)]);
      $this->progressUpdates = array_values($this->progressUpdates);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addProgressUpdateAt($aProgressUpdate, $index)
  {  
    $wasAdded = false;
    if($this->addProgressUpdate($aProgressUpdate))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfProgressUpdates()) { $index = $this->numberOfProgressUpdates() - 1; }
      array_splice($this->progressUpdates, $this->indexOfProgressUpdate($aProgressUpdate), 1);
      array_splice($this->progressUpdates, $index, 0, array($aProgressUpdate));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveProgressUpdateAt($aProgressUpdate, $index)
  {
    $wasAdded = false;
    if($this->indexOfProgressUpdate($aProgressUpdate) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfProgressUpdates()) { $index = $this->numberOfProgressUpdates() - 1; }
      array_splice($this->progressUpdates, $this->indexOfProgressUpdate($aProgressUpdate), 1);
      array_splice($this->progressUpdates, $index, 0, array($aProgressUpdate));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addProgressUpdateAt($aProgressUpdate, $index);
    }
    return $wasAdded;
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

  public static function minimumNumberOfFundingAccount()
  {
    return 0;
  }

  public function addFundingAccount($aFundingAccount)
  {
    $wasAdded = false;
    if ($this->indexOfFundingAccount($aFundingAccount) !== -1) { return false; }
    $this->fundingAccount[] = $aFundingAccount;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeFundingAccount($aFundingAccount)
  {
    $wasRemoved = false;
    if ($this->indexOfFundingAccount($aFundingAccount) != -1)
    {
      unset($this->fundingAccount[$this->indexOfFundingAccount($aFundingAccount)]);
      $this->fundingAccount = array_values($this->fundingAccount);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addFundingAccountAt($aFundingAccount, $index)
  {  
    $wasAdded = false;
    if($this->addFundingAccount($aFundingAccount))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfFundingAccount()) { $index = $this->numberOfFundingAccount() - 1; }
      array_splice($this->fundingAccount, $this->indexOfFundingAccount($aFundingAccount), 1);
      array_splice($this->fundingAccount, $index, 0, array($aFundingAccount));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveFundingAccountAt($aFundingAccount, $index)
  {
    $wasAdded = false;
    if($this->indexOfFundingAccount($aFundingAccount) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfFundingAccount()) { $index = $this->numberOfFundingAccount() - 1; }
      array_splice($this->fundingAccount, $this->indexOfFundingAccount($aFundingAccount), 1);
      array_splice($this->fundingAccount, $index, 0, array($aFundingAccount));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addFundingAccountAt($aFundingAccount, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $this->employees = array();
    $this->items = array();
    $this->progressUpdates = array();
    $this->transactions = array();
    $this->fundingAccount = array();
  }

}
?>