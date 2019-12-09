<html>
	<head>
		<title> Funding Account Manager</title>
	</head>
		<body>
	
<?php 

$my_dir = dirname(__FILE__);

require_once $my_dir . '\model\LaboratoryManager.php';
require_once $my_dir . '\model\FundingAccount.php';
require_once $my_dir . '\persistence\Persistence.php';
require_once dirname(__FILE__) . '\controller\Controller.php';

session_start();

$c = new Controller();
try {
    $c->updatePayroll();
    $_SESSION["errorPayroll"] = "";
} catch (Exception $e) {
    $_SESSION["errorPayroll"] = $e->getMessage();
}
//Make sure that there is a funding account associated to the lab before loading the lab
$checker = new FundingChecker();
$checker->initiateFunding();

$pm = new Persistence();
$lab = $pm->loadDataFromStore();

    $myAccount = $lab->getFundingAccount_index(0);
    
    echo "Current Balance :"."&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" . $myAccount->getBalance() . " $";
    
    echo "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp";
?>

<!-- Link to Fund Adder page -->	
<a href="fundsAdder.php">Add funds</a><br><br><br><br>
<!-- Link to Display Report page -->	
<a href="displayReport.php">View report</a><br><br>
<!-- Link to Add Misc Transaction page -->	
<a href="transactionAdder.php">Add miscelaneous transaction</a><br>
			
	</body>