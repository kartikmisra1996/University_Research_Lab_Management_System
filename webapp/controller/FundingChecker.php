<?php

$my_dir = dirname(__FILE__);
require_once $my_dir . '/../persistence/Persistence.php';
require_once $my_dir . '/../model/Employee.php';
require_once $my_dir . '/../model/LaboratoryManager.php';
require_once $my_dir . '/../model/ProgressUpdate.php';
require_once $my_dir . '/../model/Item.php';
require_once $my_dir . '/../model/Supply.php';
require_once $my_dir . '/../model/Transaction.php';
require_once $my_dir . '/../model/Equipment.php';
require_once $my_dir . '/../model/FundingAccount.php';


class FundingChecker
{
    public function __construct()
    {
    }
    
    public function initiateFunding() {
        $pm = new Persistence();
        $lab = $pm->loadDataFromStore();
        //Check existence of funding account
        if (!($lab->hasFundingAccount())) {
            
            $myAccount = new FundingAccount(0);
            $lab->addFundingAccount($myAccount);
            
            //Write all of the data
            $pm->writeDataToStore($lab);
        }
    }
    
    
}