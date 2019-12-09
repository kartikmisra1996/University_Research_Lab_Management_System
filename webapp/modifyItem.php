<?php

$my_dir = dirname(__FILE__);
require_once $my_dir . '/controller/Controller.php';

session_start();

$itemname = $_GET['name'];
$itemprice = $_GET['price'];
$itemnewquantity = $_GET['newquantity'];
$itemprevquantity = $_GET['previousquantity'];
$itemindex= $_GET['index'];


$pm = new Persistence();
$lab = $pm->loadDataFromStore();

$c = new Controller();
try {
    $c->modifyItem($itemindex, $itemname, $itemprice, $itemprevquantity, $itemnewquantity);
    $_SESSION["errorItemName"] = "";
} catch (Exception $e) {
    $_SESSION["errorItemName"] = $e->getMessage();
}
?>

<html>
	<head>
		<meta charset="UTF-8">
        <meta http-equiv="refresh" content="1; url=manageEmployees.php">
			<?php
            echo "<script type='text/javascript'>";
            $myItem = $lab->getItem_index($itemindex);
            //Redirect to different pages depending on the item of the item quipment or suppy
            if (get_class($myItem) == "Supply") {
                echo "window.location.href = 'manageSupplies.php'";
            } else if (get_class($myItem) == "Equipment") {
                echo "window.location.href = 'manageEquipment.php'";
            }
            echo "</script>"; ?>
    <title>Page Redirection</title>
    </head>
    

    
    
    </html>