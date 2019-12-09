<?php 

$my_dir = dirname(__FILE__);

require_once $my_dir . '\model\LaboratoryManager.php';
require_once $my_dir . '\model\FundingAccount.php';
require_once $my_dir . '\persistence\Persistence.php';
//require_once $my_dir . '\controller\Controller.php';

require_once dirname(__FILE__) . '\controller\Controller.php';


session_start();


$c = new Controller();

$c = new Controller();
try {
    $c->updatePayroll();
    $_SESSION["errorPayroll"] = "";
} catch (Exception $e) {
    $_SESSION["errorPayroll"] = $e->getMessage();
}


$checker = new FundingChecker();
$checker->initiateFunding();

$pm = new Persistence();
$lab = $pm->loadDataFromStore();

    

    $myAccount = $lab->getFundingAccount_index(0);
 
    
    echo "Current Balance :"."&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" . $myAccount->getBalance() . " $";
    echo '<br><br><br><br><br>';
    echo "-----------------------------------".'<br>';
    echo "Transactions".'<br>';
    echo "-----------------------------------".'<br>';
    echo '<br><br>';

    
    
    for ($i = 0; $i < $myAccount->numberOfTransactions(); $i++) {
        $myTransaction = $myAccount->getTransaction_index($i);
        $arr[$i] = $myTransaction;
    }
    
    
    if (isset($arr)) {
        usort($arr, 'sortTransactionsByDate');
    }
    
    
    for ($i = 0; $i < $myAccount->numberOfTransactions(); $i++) {
        
        echo $arr[$i]->getDate()."&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"."||"."&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp";
        if ($arr[$i]->getAmount() > 0) {
            echo "+".$arr[$i]->getAmount()." $";
        } else {
            echo $arr[$i]->getAmount()." $";
            
        }
        echo '<br><br>';
        echo "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp".$arr[$i]->getDescription().'<br><br>';
        echo "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"."---";
        echo '<br><br><br><br>';
    }
    
    
    function sortTransactionsByDate($a, $b) {
        if($a->getDate() == $b->getDate()){ return 0 ; }
        return ($a->getDate() < $b->getDate()) ? 1 : -1;
    }
    
   
    
?>