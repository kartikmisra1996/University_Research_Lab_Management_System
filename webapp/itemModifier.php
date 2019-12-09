<html>
	<head>
		<title> Item Modifier</title>
	</head>
		<body>

<?php
$my_dir = dirname(__FILE__);
require_once $my_dir . '/controller/Controller.php';

session_start();

//Receive employee index through GET method
$itemindex = $_GET['index'];

$pm = new Persistence();
$lab = $pm->loadDataFromStore();

$myItem = $lab->getItem_index($itemindex);

?>
		<!-- "Modify Item" submission form through GET method -->
		<!-- Default value inside each text field is the original value for the item attributes -->
		<form action="modifyItem.php" method="get">
		Name:<input type="text" name="name" value="<?php echo $myItem->getName(); ?>"/>
		Base price:<input type="text" name="price" value="<?php echo $myItem->getPrice(); ?>"/>
		Quantity:<input type="text" name="newquantity" value="<?php echo $myItem->getQuantity(); ?>"/>
		<input type="hidden" name="index" value="<?php echo $itemindex; ?>"/>
		<input type="hidden" name="previousquantity" value="<?php echo $myItem->getQuantity() ?>"/>
		<input type="submit" value="save changes">
</form>