<html>
	<head>
		<title> Adding Equipment</title>
	</head>
	<body>
	
<?php
$my_dir = dirname(__FILE__);
require_once $my_dir . '/controller/Controller.php';

session_start();

//Receive equipment attributes through GET method
$equipmentname = $_GET['name'];
$equipmentprice = $_GET['price'];
$equipmentquantity = $_GET['quantity'];

$c = new Controller();
try {
    $c->createEquipment($equipmentname, $equipmentprice, $equipmentquantity);
    $_SESSION["errorEquipmentName"] = "";
    //Catch exception for input validation checks
} catch (Exception $EquipmentName) {
    $_SESSION["errorEquipmentName"] = $e->getMessage();
}
?>

<html>
 <head>
  <!-- Redirect to manageEquipment page with new equipment -->
        <meta charset="UTF-8">
        <meta http-equiv="refresh" content="1; url=manageEquipment.php">
        <script type="text/javascript">
            window.location.href = "manageEquipment.php"
        </script>
        <title>Page Redirection</title>
    </head>
</html>



