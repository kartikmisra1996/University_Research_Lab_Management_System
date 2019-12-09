<html>
	<head>
		<title> Adding Supply</title>
	</head>
	<body>

<?php
$my_dir = dirname(__FILE__);
require_once $my_dir . '/controller/Controller.php';

session_start();

//Receive supply attributes through GET method
$supplyname = $_GET['name'];
$supplyprice = $_GET['price'];
$supplyquantity = $_GET['quantity'];
if ($supplyquantity > 0) {
    $supplyavailable = true;
} else {
    $supplyavailable = false;
}

$c = new Controller();
try {
    $c->createSupply($supplyname, $supplyprice, $supplyquantity, $supplyavailable);
    $_SESSION["errorSupplyName"] = "";
    //Catch exception for input validation checks
} catch (Exception $SupplyName) {
    $_SESSION["errorSupplyName"] = $e->getMessage();
}
?>

<html>
 <head>
 <!-- Redirect to manageSupplies page -->
        <meta charset="UTF-8">
        <meta http-equiv="refresh" content="1; url=manageSupplies.php">
        <script type="text/javascript">
            window.location.href = "manageSupplies.php"
        </script>
        <title>Page Redirection</title>
    </head>



</html>
